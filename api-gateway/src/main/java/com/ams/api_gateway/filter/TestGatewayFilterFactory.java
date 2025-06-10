package com.ams.api_gateway.filter;

import com.ams.api_gateway.dto.ValidateResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TestGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final WebClient webClient;

    public TestGatewayFilterFactory(
            @Value("${auth.server.url:http://localhost:4044}") String serverUrl,
            WebClient.Builder webClient) {
        this.webClient =  webClient.baseUrl(serverUrl).build();
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getPath().value();
            System.out.println("------------ REMOVE LATER ------------Path: " + path);

            return null;
        };
    }

}
