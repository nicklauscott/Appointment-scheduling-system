package com.ams.tenant_service.multitenancy.schema;

import com.ams.tenant_service.multitenancy.schema.schema_resolver.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
public class TenantFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String staffId = request.getHeader("X-USER-ID");
        String staffRole =  (request.getHeader("X-USER-ROLE") == null) ? "USER" : request.getHeader("X-USER-ROLE");

        if (staffId == null || staffId.isBlank()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Missing X-USER-ID header\"}");
            return;
        }

        if (staffRole == null || staffRole.isBlank()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Missing X-USER-ROLE header\"}");
            return;
        }

        if (!staffRole.equals("ADMIN")) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"You are not allowed to make this request\"}");
            return;
        }

        TenantContext.INSTANCE.setCurrentTenant("public");
        Map<String, String> requestDetail = Map.of("X-USER-ID", "", "X-USER-ROLE", staffRole);
        TenantContext.INSTANCE.setRequestDetail(requestDetail);
        try {
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.INSTANCE.clear();
        }
    }
}
