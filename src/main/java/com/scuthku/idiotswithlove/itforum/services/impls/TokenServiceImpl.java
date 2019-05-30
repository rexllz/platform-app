package com.scuthku.idiotswithlove.itforum.services.impls;

import java.util.UUID;

import com.scuthku.idiotswithlove.itforum.dto.UserDTO;
import com.scuthku.idiotswithlove.itforum.enums.TokenEnum;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.scuthku.idiotswithlove.itforum.entities.User;
import com.scuthku.idiotswithlove.itforum.entities.Token;
import com.scuthku.idiotswithlove.itforum.services.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    private Integer MAX_TOKEN_AMOUNT=10;

    private Integer TOKEN_EXPIRES_SECONDS=86400;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String createToken(UserDTO userDTO) {

        try {
            Token token = new Token(userDTO.getUserId().toString(), UUID.randomUUID().toString().replace("-", ""));

            /*code here for check redis*/

            redisTemplate.boundZSetOps(token.getUserId()).removeRangeByScore(0, (System.currentTimeMillis() - (TOKEN_EXPIRES_SECONDS) * 1000));

            int tmpCount = redisTemplate.boundZSetOps(token.getUserId()).size().intValue() - (MAX_TOKEN_AMOUNT);

            if (tmpCount >= 0) {
                for (int i = 0; i <= tmpCount; i++) {
                    redisTemplate.delete(redisTemplate.boundZSetOps(token.getUserId()).range(0, -1).iterator().next());
                    redisTemplate.boundZSetOps(token.getUserId()).removeRange(0, 0);
                }
            }
            redisTemplate.boundZSetOps(token.getUserId()).add(token.getValue(), System.currentTimeMillis());

            /**Insert the token to Redis by using token.getUserId().toSting() + "-" + token.getValue().*/

            return wrapToDTO(token);
        } catch (RuntimeException e) {
            throw new TokenException(TokenEnum.FAIL_TO_CREATE_TOKEN);
        }
    }

    @Override
    public boolean deleteToken(String tokenDTO) {
        Token token = wrapToEntity(tokenDTO);
        redisTemplate.boundZSetOps(token.getUserId()).remove(token.getValue());
        return true;
    }

    @Override
    public boolean verifyToken(String tokenDTO) {
        Token token = wrapToEntity(tokenDTO);

        redisTemplate.boundZSetOps(token.getUserId())
                .removeRangeByScore(0, (System.currentTimeMillis()-Integer.valueOf(TOKEN_EXPIRES_SECONDS)*1000));
        if (!isPresent(token)) {
            throw new TokenException(TokenEnum.NO_EXISTED_TOKEN);
        }
        return true;
    }

    /**Should be injected into the posthandle function of the specific interceptor.*/
    private String wrapToDTO(Token token) {
        return token.getUserId() + "-" + token.getValue();
    }

    /**Should be injected into the posthandle function of the specific interceptor.*/
    private Token wrapToEntity(String token) {
        try {
            String[] retvals = token.split("-", 2); /**The limitation 2 must be used in constant.*/
            return new Token(retvals[0], retvals[1]);
        } catch (RuntimeException e) {
            throw new TokenException(TokenEnum.WRAP_WRONG);
        }
    }
    private boolean isPresent(Token token) {
        if(redisTemplate.boundZSetOps(token.getUserId()).rank(token.getValue()).equals(null)) {
            return false;
        }
        return true;
    }
}
