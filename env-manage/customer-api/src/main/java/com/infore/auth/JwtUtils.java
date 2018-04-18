package com.infore.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.infore.common.enums.ErrorTypeEnum;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.common.constant.Const.Jwt;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xuyao on 2017/7/7.
 */
public class JwtUtils {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private String secretKey;
    private int expiredAfterMins=0;
    private static final String ISSUER = "qhManager";
    private Algorithm algorithm = null;

    //无法不带参数初始化
    private JwtUtils() {
    }

    /**
     * @param secretKey Jwt Algorithm 生成的key
     * @param expiredAfterMins 过期时间:分钟
     */
    public JwtUtils(String secretKey, int expiredAfterMins) throws UnsupportedEncodingException {
        this.secretKey = secretKey;
        this.expiredAfterMins = expiredAfterMins;
        algorithm = Algorithm.HMAC256(secretKey);
    }

    public JwtUtils(String secretKey) throws UnsupportedEncodingException {
        this.secretKey = secretKey;
        algorithm = Algorithm.HMAC256(secretKey);
    }

    /**
     * 根据名称生产token
     */
    public String generateTokenserName(String name) {
        String token = null;
        try {
            Date dateExp = null;
            if (expiredAfterMins != 0) {
                dateExp = DateTime.now().plusMinutes(expiredAfterMins).toDate();
            }
            token = generateJWT()
                .withClaim(Jwt.LOGIN_NAME, name)//登录名key:ln
                .withExpiresAt(dateExp)
                .withIssuedAt(new Date())
                .sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            log.error("generateTokenserName error:{}", exception);
            throw new ServerRuntimeException("Jwt token fail", exception.getMessage());
        }
        return token;
    }
    /**
     * 根据名称生产token
     */
    public String generateTokenserName(Map<String,String> claimMap) {
        String token = null;
        try {
            Date dateExp = null;
            if (expiredAfterMins != 0) {
                dateExp = DateTime.now().plusMinutes(expiredAfterMins).toDate();
            }
            Builder jwtBuilder = generateJWT();
            claimMap.forEach((k,v)->jwtBuilder.withClaim(k,v));
            token = jwtBuilder
                .withExpiresAt(dateExp)
                .withIssuedAt(new Date())
                .sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            log.error("generateTokenserName error:{}", exception);
            throw new ServerRuntimeException("Jwt token fail", exception.getMessage());
        }
        return token;
    }

    /**
     * 验证token,本身是否ok
     * @return true:验证通过,exception:验证失败
     */
    public boolean verifyToken(String token) {
        boolean jwt = true;
        try {
            JWTVerifier verifier = verifierJWT();
            verifier.verify(token);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            log.error("JWTVerification error:{}", exception.getMessage());
            throw new ServerRuntimeException(ErrorTypeEnum.TOKEN_ERROR.getCode());
        }
        return jwt;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public DecodedJWT decodeToken(String token){
        DecodedJWT jwt = null;
        try {
            jwt= JWT.decode(token);
        } catch (JWTDecodeException exception){
            log.error("JWTDecodeException error:{}", exception);
            throw new ServerRuntimeException("decode fail", exception.getMessage());
        }
        return jwt;
    }

    private Builder generateJWT() {
        return JWT.create()
            .withIssuer(ISSUER)
            .withIssuedAt(new Date());
    }

    private JWTVerifier verifierJWT() {
        return JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build();
    }

    public static void main(String[] args) {

    }

}
