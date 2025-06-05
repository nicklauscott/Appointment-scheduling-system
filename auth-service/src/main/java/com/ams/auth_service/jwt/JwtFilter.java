package com.ams.auth_service.jwt;

import com.ams.auth_service.exception.InvalidOrExpiredTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if ("/tenant/register_staff".equals(request.getRequestURI())
                && "POST".equalsIgnoreCase(request.getMethod())) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"You need to be authenticated to use this feature\"}");
                return;
            }

            try {
                Pair<String, String> claims = jwtService.getEmailAndTenantIdFromToken(authHeader);
                String role = jwtService.getRoleFromToken(authHeader);

                if (!role.equals("ADMIN")) {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType("application/json");
                    response.getWriter().write("{\"message\": \"You are not allowed to use this feature\"}");
                    return;
                }

                Map<String, String> requestDetail = new HashMap<>();
                requestDetail.put("X-TENANT-ID", claims.getRight());
                TenantContext.INSTANCE.setRequestDetail(requestDetail);

                var auth = new UsernamePasswordAuthenticationToken(claims.getLeft(),
                        new SimpleGrantedAuthority(role), Collections.emptyList());
                var context = SecurityContextHolder.getContext();
                context.setAuthentication(auth);

            } catch (InvalidOrExpiredTokenException e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"You need to be authenticated to use this feature\"}");
                return;
            } finally {
                TenantContext.INSTANCE.clear();
            }
        }

        filterChain.doFilter(request, response);
    }

}
