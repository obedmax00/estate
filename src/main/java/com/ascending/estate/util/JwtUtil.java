package com.ascending.estate.util;

/*
 * Copyright (C) 2019 Liwei Wang, daveywang@live.com - All Rights Reserved
 */
import com.ascending.estate.model.Customer;
import com.ascending.estate.model.Role;
import com.ascending.estate.model.Agent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class JwtUtil {
    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static final String SECRET_KEY = System.getProperty("secret.key");
    private static final String ISSUER = "com.ascending";
    private static final long EXPIRATION_TIME = 86400 * 1000;
    public static String generateToken(Agent agent) {
        //JWT signature algorithm using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //Sign JWT with SECRET_KEY
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(agent.getId()));
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        Set<Role> roles = agent.getRoles();
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";
        //String allowedResource = roles.stream().map(role -> role.getAllowedResource()).collect(Collectors.joining(","));
        //claims.put("allowedResource", allowedResource);
        for (Role role : roles) {
            if (role.isAllowedRead()) allowedReadResources = String.join(role.getAllowedResource(), allowedReadResources, ",");
            if (role.isAllowedCreate()) allowedCreateResources = String.join(role.getAllowedResource(), allowedCreateResources, ",");
            if (role.isAllowedUpdate()) allowedUpdateResources = String.join(role.getAllowedResource(), allowedUpdateResources, ",");
            if (role.isAllowedDelete()) allowedDeleteResources = String.join(role.getAllowedResource(), allowedDeleteResources, ",");
        }
        claims.put("allowedReadResources", allowedReadResources.replaceAll(".$", ""));
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(".$", ""));
        claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(".$", ""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(".$", ""));
        //Set the JWT Claims
        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, signingKey);
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
    public static String generateToken(Customer customer) {
        //JWT signature algorithm using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //Sign JWT with SECRET_KEY
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(customer.getId()));
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));


        // the uri ignored is used by add not to uri and must be in the beginning of the string
        String allowedReadResources = String.format("not/houses/view-customers,/houses,/customers/%s,/customers/view-agent/%s",
                customer.getName(), customer.getName());
        String allowedCreateResources = "";
        String allowedUpdateResources = String.format("/customers/%s",customer.getName());
        String allowedDeleteResources = "";
        logger.debug(allowedReadResources);
        //String allowedResource = roles.stream().map(role -> role.getAllowedResource()).collect(Collectors.joining(","));
        //claims.put("allowedResource", allowedResource);
        claims.put("allowedReadResources", allowedReadResources);
        claims.put("allowedCreateResources", allowedCreateResources);
        claims.put("allowedUpdateResources", allowedUpdateResources);
        claims.put("allowedDeleteResources", allowedDeleteResources);
        //Set the JWT Claims
        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, signingKey);
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
    public static Claims decodeJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        logger.debug("Claims: " + claims.toString());
        return claims;
    }
}




