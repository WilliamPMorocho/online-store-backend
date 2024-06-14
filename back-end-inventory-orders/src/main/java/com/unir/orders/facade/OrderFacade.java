package com.unir.orders.facade;

import com.unir.orders.model.pojo.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderFacade {
    @Value("${getProduct.url}")
    private String getOrderUrl;
    private final RestTemplate restTemplate;

    public Order getOrder(String orderId) {
        try {
            String orderUrl = String.format(getOrderUrl, orderId);
            log.info("Getting order with ID: {}, Request to {}", orderId, orderUrl);
            return restTemplate.getForObject(orderUrl, Order.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Order with ID {}", e.getStatusCode(), orderId);
            return null;
        } catch (HttpServerErrorException e) {
            log.error("Server Error: {}, Order with ID {}", e.getStatusCode(), orderId);
            return null;
        } catch (Exception e) {
            log.error("Error: {}, Order with ID {}", e.getMessage(), orderId);
            return null;
        }
    }
}
