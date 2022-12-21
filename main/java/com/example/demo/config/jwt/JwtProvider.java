package com.example.demo.config.jwt;

import com.example.demo.annotations.Loggable;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public final String generateToken(String username, String role){
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());


        return Jwts.builder()
                .setSubject(username)
                .setExpiration(date)
                .claim("user-role",role)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public final boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e){
            System.out.println("Неверный токен");
            return false;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public String getLoginFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getRoleFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return (String) claims.get("user-role");
    }
}
