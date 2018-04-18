package com.infore.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.infore.common.exception.ServerRuntimeException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;

/**
 * Created by xuyao on 2017/7/7.
 */
public class JwtTest {

    public static void main(String[] args) {

        String token = getToken();
        DecodedJWT jwt=verifyToken(token);
        String payload = jwt.getPayload();
        String jwtToken = jwt.getToken();
        String header = jwt.getHeader();
        String signature=jwt.getSignature();

        System.out.println("--payload--"+payload);
        System.out.println("--jwtToken--"+jwtToken);
        System.out.println("--header--"+header);
        System.out.println("--signature--"+signature);
        System.out.println("=======================================");
        DecodedJWT decodedJWT=decodeToken(token);
        String decodedJWTHeader=decodedJWT.getHeader()==null?"":decodedJWT.getHeader();
        String decodedJWTPayload=decodedJWT.getPayload()==null?"":decodedJWT.getPayload();
        String decodedJWTSignature=decodedJWT.getSignature()==null?"":decodedJWT.getSignature();
        String decodedJWTToken=decodedJWT.getToken()==null?"":decodedJWT.getToken();

        System.out.println("--decodedJWTHeader--"+decodedJWTHeader);
        System.out.println("--decodedJWTPayload--"+decodedJWTPayload);
        System.out.println("--decodedJWTSignature--"+decodedJWTSignature);
        System.out.println("--decodedJWTToken--"+decodedJWTToken);

        System.out.println("********************************");
        String decodedJWTIssuer=decodedJWT.getIssuer();
        Map<String, Claim>  decodedJWTClaims=decodedJWT.getClaims();
        Claim decodedJWTClaim=decodedJWT.getClaim("usr");
        String decodedJWTHeader1=decodedJWT.getHeader();

        System.out.println("--decodedJWTIssuer--"+decodedJWTIssuer);
        System.out.println("--decodedJWTClaims--"+decodedJWTClaims.toString());
        System.out.println("--decodedJWTClaim--"+decodedJWTClaim.asString());
        System.out.println("--decodedJWTHeader1--"+decodedJWTHeader1);

    }

    private static String getToken(){
        String token = null;
        try {
            Map<String, Object> headerClaims = new HashMap<>();
            headerClaims.put("oiu", "fgjh");
            Algorithm algorithm = Algorithm.HMAC256("secret");
            Date dateExp=DateTime.now().plusMinutes(5).toDate();
            token= JWT.create()
//                .withIssuer("auth0")
                .withClaim("usr","xuy")
                .withAudience("ppoop")
//                .withSubject("xuyao")
                .withHeader(headerClaims)
                .withExpiresAt(new Date())
                .withIssuedAt(new Date())
//                .withNotBefore(new Date())
                .sign(algorithm);
            //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwPCuzs
            System.out.printf("---getToken----"+token+"\n");
        } catch (UnsupportedEncodingException exception){
            exception.printStackTrace();
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception){
            exception.printStackTrace();
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    private static DecodedJWT verifyToken(String token){
        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
//                .withIssuer("auth0")
//                .acceptExpiresAt(5L)
                .build(); //Reusable verifier instance
            jwt= verifier.verify(token);
            System.out.println("-----Jwt----"+jwt);
        } catch (UnsupportedEncodingException exception){
            exception.printStackTrace();
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception){
            throw new ServerRuntimeException("404",exception.getMessage());
            //Invalid signature/claims
        }

        return jwt;
    }

    private static DecodedJWT decodeToken(String token){
        DecodedJWT jwt = null;
        try {
            jwt= JWT.decode(token);
            System.out.println("---DecodedJWT---"+jwt);
        } catch (JWTDecodeException exception){
            exception.printStackTrace();
            //Invalid token
        }
        return jwt;
    }
}
