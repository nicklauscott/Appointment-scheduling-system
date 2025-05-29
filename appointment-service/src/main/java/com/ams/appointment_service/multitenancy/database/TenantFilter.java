package com.ams.appointment_service.multitenancy.database;

import com.ams.appointment_service.multitenancy.schema.schema_resolver.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
public class TenantFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String tenant = request.getHeader("X-Tenant-ID");
        if (tenant == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"X-Tenant-ID header is required\"}");
            return;
        }

        TenantContext.INSTANCE.setCurrentTenant(tenant);
        try {
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.INSTANCE.clear();
        }
    }
}
