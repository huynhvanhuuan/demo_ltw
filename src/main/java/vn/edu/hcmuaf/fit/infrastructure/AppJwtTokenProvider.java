package vn.edu.hcmuaf.fit.infrastructure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import vn.edu.hcmuaf.fit.constant.SecurityConstant;
import vn.edu.hcmuaf.fit.domain.AppUserDomain;
import vn.edu.hcmuaf.fit.domain.authority.GrantedAuthority;
import vn.edu.hcmuaf.fit.domain.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class AppJwtTokenProvider {
    private String secret;

    public AppJwtTokenProvider() {
        Properties props = new Properties();
        try {
            props.load(AppJwtTokenProvider.class.getClassLoader().getResourceAsStream("application.properties"));
            secret = props.getProperty("jwt.secret");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateJwtToken(AppUserDomain appUser) {

        String[] claims = getClaimsFromUser(appUser);

        return JWT.create().withIssuer(SecurityConstant.COMPANY).withAudience(SecurityConstant.APPLICATION_NAME)
                .withIssuedAt(new Date()).withSubject(appUser.getUsername())
                .withArrayClaim(SecurityConstant.AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    // decode token
    /*public AppUserDomain decodeJwtToken(String token) {
        Map<String, Claim> claims = JWT.decode(token).getClaims();

        String username = getSubject(token);
        String[] authorities = (String[]) claims.get(SecurityConstant.AUTHORITIES);
        List<GrantedAuthority> grantedAuthorities = stream(authorities).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new AppUserDomain(username, grantedAuthorities);
    }*/

    private String[] getClaimsFromUser(AppUserDomain appUser) {
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : appUser.getAuthorities()) {
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities.toArray(new String[0]);
    }

    private JWTVerifier getJwtVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(SecurityConstant.COMPANY).build();
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException(SecurityConstant.TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }

    public boolean isTokenValid(String username, String token) {
        JWTVerifier verifier = getJwtVerifier();

        Date expirationDate = verifier.verify(token).getExpiresAt();
        boolean isTokenExpired = expirationDate.before(new Date());

        return username != null && !username.isEmpty() && !isTokenExpired;
    }

    public String getSubject(String token) {
        JWTVerifier verifier = getJwtVerifier();
        return verifier.verify(token).getSubject();
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        JWTVerifier verifier = getJwtVerifier();
        String[] claims = verifier.verify(token).getClaim(SecurityConstant.AUTHORITIES).asArray(String.class);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
