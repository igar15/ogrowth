package ru.javaprojects.gatewayserver.filters;

import brave.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseFilter {

    final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    @Autowired
    private Tracer tracer;

    @Autowired
    FilterUtils filterUtils;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                String traceId = "";
                try {
                    traceId = tracer.currentSpan().context().traceIdString();
                } catch (NullPointerException e) {
                    logger.debug(e.getMessage());
                }
                logger.debug("Adding the correlation id to the outbound headers. {}", traceId);
                exchange.getResponse().getHeaders().add(FilterUtils.CORRELATION_ID, traceId);
                logger.debug("Completing outgoing request for {}.", exchange.getRequest().getURI());
            }));
        };
    }
}