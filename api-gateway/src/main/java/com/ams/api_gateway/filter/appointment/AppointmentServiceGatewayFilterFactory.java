package com.ams.api_gateway.filter.appointment;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AppointmentServiceGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String tenant = exchange.getRequest().getHeaders().getFirst("X-TENANT-ID");

            String path = exchange.getRequest().getPath().value();
            System.out.println("------------ REMOVE LATER ------------Path: " + path);

            if (tenant == null) {
                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        };
    }

}
