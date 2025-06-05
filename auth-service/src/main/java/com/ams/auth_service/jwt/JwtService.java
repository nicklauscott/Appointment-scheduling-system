package com.ams.auth_service.jwt;

import com.ams.auth_service.dto.TokenPairResponseDTO;
import com.ams.auth_service.exception.InvalidOrExpiredTokenException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class JwtService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public TokenPairResponseDTO generateTokenPair(String tenantId, String email, String role) {
        String accessToken = generateAccessToken(email, role, tenantId);
        String refreshToken = generateRefreshToken(email, tenantId);
        return new TokenPairResponseDTO(accessToken, refreshToken);
    }

    public String generateAccessToken(String email, String role, String tenantId) {
        /*
        return Jwts.builder()
            .subject(email)
            .claim("role", role)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(secretKey)
            .compact();
         */

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(email)
                .claim("role", role)
                .claim("tenant", tenantId)
                .claim("type", "access")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(String email, String tenantId) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(email)
                .claim("tenant", tenantId)
                .claim("type", "refresh")
                .issuedAt(now)
                .expiresAt(now.plus(15, ChronoUnit.DAYS))
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Jwt parseAndVerify(String token) {
        try {
            String rawToken = token.startsWith("Bearer ") ? token.replace("Bearer ", "") : token;
            return decoder.decode(rawToken);
        } catch (Exception e) {
            throw new InvalidOrExpiredTokenException("Invalid or expired token 0");
        }
    }

    public String getEmailFromToken(String token) {
        Jwt jwt = parseAndVerify(token);
        return jwt.getSubject();
    }

    public String getTenantIdFromToken(String token) {
        Jwt jwt = parseAndVerify(token);
        return jwt.getClaimAsString("tenant");
    }

    public String getRoleFromToken(String token) {
        Jwt jwt = parseAndVerify(token);
        return jwt.getClaimAsString("role");
    }

    public Pair<String, String> getEmailAndTenantIdFromToken(String token) {
        Jwt jwt = parseAndVerify(token);
        String email = jwt.getSubject();
        String tenantId = jwt.getClaimAsString("tenant");
        return Pair.of(email, tenantId);
    }

}
