package com.school.management.School.utility;


import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access.expiration}")
    private long accessExpiry;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiry;

    private SecretKey getKey() {

        return Keys.hmacShaKeyFor(
                secret.getBytes(
                        StandardCharsets.UTF_8
                )
        );

    }

    public String generateToken(
            String username
    ) {

        return Jwts.builder()

                .subject(
                        username
                )

                .issuedAt(
                        new Date()
                )

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + accessExpiry
                        )
                )

                .signWith(
                        getKey()
                )

                .compact();

    }

    public String generateRefreshToken(
            String username
    ) {

        return Jwts.builder()

                .subject(
                        username
                )

                .issuedAt(
                        new Date()
                )

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + refreshExpiry
                        )
                )

                .signWith(
                        getKey()
                )

                .compact();

    }

    public String extractUser(
            String token
    ) {

        return Jwts.parser()

                .verifyWith(
                        getKey()
                )

                .build()

                .parseSignedClaims(
                        token
                )

                .getPayload()

                .getSubject();

    }

    public boolean valid(
            String token
    ) {

        try {

            extractUser(
                    token
            );

            return true;

        }
        catch (Exception e) {

            return false;

        }

    }

}
