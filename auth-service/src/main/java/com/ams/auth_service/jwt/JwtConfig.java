package com.ams.auth_service.jwt;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Configuration
public class JwtConfig {

    @Bean
    public JwtDecoder jwtDecoder() throws Exception {
        String publicKeyPath = System.getenv("JWT_PUBLIC_KEY_PATH");
        String publicKeyContent = Files.readString(Paths.get(publicKeyPath));
        RSAPublicKey publicKey = parseRSAPublicKey(publicKeyContent);
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() throws Exception {
        RSAKey rsaKey = buildRsaKey();
        var jwkSet = new JWKSet(rsaKey);
        return new NimbusJwtEncoder(new ImmutableJWKSet<>(jwkSet));
    }

    private RSAKey buildRsaKey() throws Exception {
        String privateKeyPath = System.getenv("JWT_PRIVATE_KEY_PATH");
        String publicKeyPath = System.getenv("JWT_PUBLIC_KEY_PATH");
        String privateKeyContent = Files.readString(Paths.get(privateKeyPath));
        String publicKeyContent = Files.readString(Paths.get(publicKeyPath));

        RSAPrivateKey privateKey = parseRSAPrivateKey(privateKeyContent);
        RSAPublicKey publicKey = parseRSAPublicKey(publicKeyContent);

        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID("my-key-id")
                .build();
    }

    private RSAPrivateKey parseRSAPrivateKey(String pem) throws Exception {
        String cleanedPem = pem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] keyBytes = Base64.getDecoder().decode(cleanedPem);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory factory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) factory.generatePrivate(keySpec);
    }

    private RSAPublicKey parseRSAPublicKey(String pem) throws Exception {
        String cleanedPem = pem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replace("-----BEGIN RSA PUBLIC KEY-----", "")
                .replace("-----END RSA PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] keyBytes = Base64.getDecoder().decode(cleanedPem);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory factory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) factory.generatePublic(keySpec);
    }

}
