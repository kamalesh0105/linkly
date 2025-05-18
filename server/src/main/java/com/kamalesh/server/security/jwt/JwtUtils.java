package com.kamalesh.server.security.jwt;

import com.kamalesh.server.services.UserDetailsImp;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtils {
    //Authorization Header >Bearer< Token>
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiry}")
    private String jwtExpiryMs;
    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken=request.getHeader("Authorization");
        if(bearerToken!=null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
    public String generateToken(UserDetailsImp userDetails){
        String userName=userDetails.getUsername();
        String roles=userDetails.getAuthorities().stream()
                .map(authority->authority.getAuthority())
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles",roles)
                .issuedAt(new Date())
                .expiration(new Date((new Date().getTime()+jwtExpiryMs)))
                .signWith(getKey())
                .compact();

    }
    public  String getUsernameFromJwt(String Token){
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build().parseSignedClaims(Token)
                .getPayload().getSubject();
    }
    public boolean validateToken(String authToken){
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) getKey())
                    .build().parseSignedClaims(authToken);
            return true;
        }catch (JwtException e){
            throw new RuntimeException(e);
        }catch (IllegalArgumentException e){
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private Key getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
