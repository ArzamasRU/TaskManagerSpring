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
import ru.lavrov.tm.util.SignUtil;

import javax.json.JsonException;
import java.io.IOException;
import java.util.Collection;

import static ru.lavrov.tm.service.PropertyServiceImpl.appProperties;
import static ru.lavrov.tm.util.SignUtil.getSign;

public final class TokenServiceImpl extends AbstractService implements ITokenService {

    @NotNull
    private final Bootstrap bootstrap;

    public TokenServiceImpl(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void validate(@Nullable final String token) {
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
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        sessionService.validate(curToken.getSession());
        @NotNull final String currSign = getSign(curToken,  appProperties.getProperty("salt"),
                Integer.parseInt(appProperties.getProperty("cycle")));
        if (!currSign.equals(curToken.getSignature()))
            throw new TokenSignIsInvalidException();
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
        token.setSignature(SignUtil.getSign(token, appProperties.getProperty("salt"),
                Integer.parseInt(appProperties.getProperty("cycle"))));
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String jsonToken;
        try {
            jsonToken = objectMapper.writeValueAsString(token);
        } catch (JsonProcessingException e) {
            throw new JsonException("Json writing is failed");
        }
        @NotNull final String encryptedToken = AESUtil.encrypt(jsonToken, appProperties.getProperty("key"));
        return encryptedToken;
    }
}

