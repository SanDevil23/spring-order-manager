package com.order_mgr.order_service.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.security.oauth2.jwt.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.time.Instant;
import java.util.List;

public class TokenGenerator {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Generate a key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();   // your RSA key pair

        // Build the JWT
        RSAKey rsaKey = new RSAKey.Builder((java.security.interfaces.RSAPublicKey) keyPair.getPublic())
                .privateKey((java.security.interfaces.RSAPrivateKey) keyPair.getPrivate())
                .keyID("123")
                .build();

        NimbusJwtEncoder encoder = new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(rsaKey)));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("https://example.com")
                .audience(List.of("https://example.com/resources"))
                .id("123")
                .subject("user@example.com")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        JwsHeader headers = JwsHeader.with(() -> "RS256").build();
        Jwt jwt = encoder.encode(JwtEncoderParameters.from(headers, claims));

        System.out.println(jwt.getTokenValue());
    }
}
