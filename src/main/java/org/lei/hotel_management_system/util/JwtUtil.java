package org.lei.hotel_management_system.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.lei.hotel_management_system.DTO.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("#{${jwt.expire_time}}")
    private long expireTime;

    public String createToken(String username) {
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer("auth0")                 // 发行人
                .withIssuedAt(now)                   // 令牌发行时间
                .withExpiresAt(new Date(now.getTime() + expireTime)) // 令牌过期时间
                .withClaim("username", username)     // 添加用户身份标识
                .sign(algorithm);
    }

    public TokenDTO createTokenJson(String username) {
        return new TokenDTO(createToken(username));
    }

    /**
     * 验证 Token 并返回解码后的 JWT
     *
     * @param token 令牌字符串
     * @return 解码后的 JWT 对象
     * @throws JWTVerificationException 验证失败时抛出异常
     */
    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); // 生成验证器
        String[] s = token.split(" ");
        return verifier.verify(s[s.length - 1]);
    }

    /**
     * 从 Token 中获取用户名
     *
     * @param token 令牌字符串
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        try {
            DecodedJWT jwt = verifyToken(token);
            return jwt.getClaim("username").asString();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(e.getMessage());
        }
    }

}
