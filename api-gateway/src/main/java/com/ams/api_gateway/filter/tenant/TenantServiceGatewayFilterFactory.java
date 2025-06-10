package com.ams.api_gateway.filter.tenant;

import com.ams.api_gateway.dto.ValidateResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TenantServiceGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final WebClient webClient;

    public TenantServiceGatewayFilterFactory(
            @Value("${auth.server.url:http://localhost:4044}") String serverUrl,
            WebClient.Builder webClient) {
        this.webClient =  webClient.baseUrl(serverUrl).build();
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            String tenant = exchange.getRequest().getHeaders().getFirst("X-TENANT-ID");
            String user = exchange.getRequest().getHeaders().getFirst("X-USER-ID");
            String role = exchange.getRequest().getHeaders().getFirst("X-USER-ROLE");

            String path = exchange.getRequest().getPath().value();
            System.out.println("------------ REMOVE LATER ------------Path: " + path);

            if (tenant != null || user != null || role != null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            if (token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }


            return webClient.get()
                    .uri("/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .bodyToMono(ValidateResponseDTO.class)
                    .flatMap(response -> {
                        var request = exchange.getRequest().mutate()
                                .header("X-TENANT-ID", response.getTenantId())
                                .header("X-USER-ID", response.getUserId())
                                .header("X-USER-ROLE", response.getUserRole())
                                .build();
                        var mutate = exchange.mutate().request(request).build();
                        return chain.filter(mutate);
                    })
                    .onErrorResume(ex -> {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

}
