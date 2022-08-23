package com.afk.cloudrive.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 19:15
 * @Description: com.afk.cloudrive
 */
public class TokenUtil {
    private static final String ENCRYPT_KEY = "CloudDrive";

    private static final long EXPIRE_DATE = 60 * 60 * 1000;

    /**
     * 通过用户名生成一个token
     * @param username
     * @return
     */
    public static String generateTokenByUsername(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime + EXPIRE_DATE);
        String token = JWT.create()
                .withHeader(map)
                .withClaim("username", username)
                .withClaim("password", password)
                .withIssuedAt(new Date(currentTime))
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(com.afk.cloudrive.util.TokenUtil.ENCRYPT_KEY));
        return token;
    }

    /**
     * 传入token, 进行验证
     * @param token
     * @return
     */
    public static Boolean verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(com.afk.cloudrive.util.TokenUtil.ENCRYPT_KEY)).build();
        try {
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从token解析用户名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            System.out.println("token解析用户名失败!");
            return null;
        }
    }
}
