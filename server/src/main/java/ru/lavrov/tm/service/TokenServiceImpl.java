package ru.lavrov.tm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.lavrov.tm.api.service.ISessionService;
import ru.lavrov.tm.api.service.ITokenService;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Token;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.security.TokenIsInvalidException;
import ru.lavrov.tm.exception.security.TokenSignIsInvalidException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;
import ru.lavrov.tm.util.AESUtil;

import java.io.IOException;
import java.util.Collection;

import static ru.lavrov.tm.util.SignUtil.getSign;

@Service
public final class TokenServiceImpl implements ITokenService {

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private Environment environment;

    @Override
    public void validate(@NotNull final String token, @Nullable final Collection<Role> roles) {
        if (token == null)
            throw new TokenIsInvalidException();
        @NotNull final Token curToken = decryptToken(token);
        @Nullable final String currSign = curToken.getSign();
        curToken.setSign(null);
        @Nullable final String resultSign = getSign(curToken,  environment.getProperty("salt"),
                Integer.parseInt(environment.getProperty("cycle")));
        if (resultSign == null || resultSign.isEmpty())
            throw new TokenSignIsInvalidException();
        if (!resultSign.equals(currSign))
            throw new TokenSignIsInvalidException();
        curToken.setSign(currSign);
        sessionService.validate(curToken.getSession(), roles);
    }

    @Override
    public @Nullable String login(@NotNull final String login, @NotNull final String password) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        @Nullable final Session session = sessionService.login(login, password);
        if (session == null)
            return null;
        @NotNull final Token token = new Token(session);
        token.setSign(getSign(token, environment.getProperty("salt"),
                Integer.parseInt(environment.getProperty("cycle"))));
        @NotNull final String encryptedToken = encryptToken(token);
        return encryptedToken;
    }


    @Override
    public @NotNull Token decryptToken(@NotNull final String token) {
        if (token == null || token.isEmpty())
            throw new TokenIsInvalidException();
        @NotNull final String decryptedToken = AESUtil.decrypt(token, environment.getProperty("key"));
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull Token curToken;
        try {
            curToken = objectMapper.readValue(decryptedToken, Token.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TokenIsInvalidException();
        }
        return curToken;
    }

    @Override
    public @NotNull String encryptToken(@NotNull final Token token) {
        if (token == null)
            throw new TokenIsInvalidException();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String jsonToken;
        try {
            jsonToken = objectMapper.writeValueAsString(token);
        } catch (JsonProcessingException e) {
            throw new TokenIsInvalidException();
        }
        return AESUtil.encrypt(jsonToken, environment.getProperty("key"));
    }
}

