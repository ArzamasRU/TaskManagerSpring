package ru.lavrov.tm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.api.ITokenService;
import ru.lavrov.tm.bootstrap.Bootstrap;
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

import static ru.lavrov.tm.service.AppPropertyServiceImpl.appProperties;
import static ru.lavrov.tm.util.SignUtil.getSign;

public final class TokenServiceImpl extends AbstractService implements ITokenService {

    public TokenServiceImpl(@NotNull Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void validate(@NotNull final String token, @Nullable final Collection<Role> roles) {
        if (token == null)
            throw new TokenIsInvalidException();
        @NotNull final Token curToken = decryptToken(token);
        @Nullable final String currSign = curToken.getSign();
        curToken.setSign(null);
        @Nullable final String resultSign = getSign(curToken,  appProperties.getProperty("salt"),
                appProperties.getIntProperty("cycle"));
        if (resultSign == null || resultSign.isEmpty())
            throw new TokenSignIsInvalidException();
        if (!resultSign.equals(currSign))
            throw new TokenSignIsInvalidException();
        curToken.setSign(currSign);
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        sessionService.validate(curToken.getSession(), roles);
    }

    @Override
    public @Nullable String login(@NotNull final String login, @NotNull final String password) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        @Nullable final Session session = sessionService.login(login, password);
        if (session == null)
            return null;
        @NotNull final Token token = new Token(session);
        token.setSign(getSign(token, appProperties.getProperty("salt"),appProperties.getIntProperty("cycle")));
        @NotNull final String encryptedToken = encryptToken(token);
        return encryptedToken;
    }


    @Override
    public @NotNull Token decryptToken(@NotNull final String token) {
        if (token == null || token.isEmpty())
            throw new TokenIsInvalidException();
        @NotNull final String decryptedToken = AESUtil.decrypt(token, appProperties.getProperty("key"));
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
        return AESUtil.encrypt(jsonToken, appProperties.getProperty("key"));
    }
}

