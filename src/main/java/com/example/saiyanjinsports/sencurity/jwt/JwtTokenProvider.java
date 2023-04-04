package com.example.saiyanjinsports.sencurity.jwt;

import com.example.saiyanjinsports.sencurity.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String JWT_SECRET = "Toanh";
    private final long JWT_EXP = 1800000000;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl myUserDetails = (UserDetailsImpl) authentication.getPrincipal();
        Date now = new Date();
        Date expDate = new Date(now.getTime() + JWT_EXP);
        System.out.println(expDate);
        return Jwts.builder()
                .setSubject(myUserDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUserNameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Jwt không đúng định dạng");
        } catch (ExpiredJwtException ex) {
            System.out.println("Jwt hết hạn");
        } catch (UnsupportedJwtException ex) {
            System.out.println("không được hỗ trợ");
        } catch (IllegalArgumentException ex) {
            System.out.println("Đối số bất hợp pháp");
        }
        return false;
    }


}
