package ru.lavrov.tm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.api.ITokenService;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.dto.Session;
import ru.lavrov.tm.dto.Token;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.security.TokenIsInvalidException;
import ru.lavrov.tm.exception.security.TokenSignIsInvalidException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;
import ru.lavrov.tm.util.AESUtil;

import javax.json.JsonException;
import java.io.IOException;
import java.util.Collection;

import static ru.lavrov.tm.service.AppPropertyServiceImpl.appProperties;
import static ru.lavrov.tm.util.SignUtil.getSign;

public final class TokenServiceImpl extends AbstractService implements ITokenService {

    @NotNull
    private final Bootstrap bootstrap;

    public TokenServiceImpl(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void validate(@Nullable final String token, @Nullable final Collection<Role> roles) {
        @Nullable final Token curToken = decryptToken(token);
        @Nullable final String currSign = curToken.getSign();
        curToken.setSign(null);
        @NotNull final String resultSign = getSign(curToken,  appProperties.getProperty("salt"),
                appProperties.getIntProperty("cycle"));
        if (!resultSign.equals(currSign))
            throw new TokenSignIsInvalidException();
        curToken.setSign(currSign);
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        sessionService.validate(curToken.getSession(), roles);
    }

    @NotNull
    @Override
    public String login(@NotNull final String login, @NotNull final String password) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        @NotNull final Session session = sessionService.login(login, password);
        @NotNull final Token token = new Token();
        token.setSession(session);
        token.setSign(getSign(token, appProperties.getProperty("salt"),
                appProperties.getIntProperty("cycle")));
        @Nullable final String encryptedToken = encryptToken(token);
        return encryptedToken;
    }

    @Nullable
    @Override
    public Token decryptToken(@Nullable final String token) {
        if (token == null)
            throw new TokenIsInvalidException();
        @NotNull final String decryptedToken = AESUtil.decrypt(token, appProperties.getProperty("key"));
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @Nullable Token curToken;
        try {
            curToken = objectMapper.readValue(decryptedToken, Token.class);
        } catch (IOException e) {
            throw new JsonException("Json reading is failed");
        }
        return curToken;
    }

    @Nullable
    @Override
    public String encryptToken(@Nullable final Token token) {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String jsonToken;
        try {
            jsonToken = objectMapper.writeValueAsString(token);
        } catch (JsonProcessingException e) {
            throw new JsonException("Json writing is failed");
        }
        return AESUtil.encrypt(jsonToken, appProperties.getProperty("key"));
    }
}

