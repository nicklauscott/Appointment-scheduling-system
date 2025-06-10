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
public class StaffFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if (request.getRequestURI().equals("/v3/api-docs")) {
            filterChain.doFilter(request, response);
        }

        String tenant = request.getHeader("X-Tenant-ID");
        String staffId = request.getHeader("X-USER-ID");
        String staffRole =  (request.getHeader("X-USER-ROLE") == null)
                ? "USER" : request.getHeader("X-USER-ROLE");
        if (tenant == null || tenant.equals("public")) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Missing or invalid X-Tenant-ID header\"}");
            return;
        }
        if (staffId == null || staffId.isBlank()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Missing X-USER-ID header\"}");
            return;
        }
        TenantContext.INSTANCE.setCurrentTenant(tenant);
        Map<String, String> requestDetail = Map.of("X-USER-ID", staffId, "X-USER-ROLE", staffRole);
        TenantContext.INSTANCE.setRequestDetail(requestDetail);
        try {
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.INSTANCE.clear();
        }
    }
}
