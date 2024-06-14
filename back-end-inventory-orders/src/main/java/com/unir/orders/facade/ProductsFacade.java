package com.unir.orders.facade;

import com.unir.orders.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductsFacade {

    @Value("${getProduct.url}")
    private String getProductUrl;

    private final RestTemplate restTemplate;
//    private final WebClient webClient;

    private final WebClient.Builder webClientBuilder;

    public Product getProduct(String id) {

        try {
            String url = String.format(getProductUrl, id);
            log.info("Getting product with ID {}. Request to {}", id, url);
            return restTemplate.getForObject(url, Product.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        } catch (HttpServerErrorException e) {
            log.error("Server Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        } catch (Exception e) {
            log.error("Error: {}, Product with ID {}", e.getMessage(), id);
            return null;
        }
    }

    public Mono<Product> patchProduct(String id, String body) {
        try {
            String url = String.format(getProductUrl, id);
            log.info("Patching product with ID {}. Request to {}", id, url);
            return webClientBuilder.build()
                    .patch()
                    .uri(url)
                    .body(Mono.just(body), String.class)
                    .retrieve()
                    .bodyToMono(Product.class);
        } catch (WebClientResponseException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        } catch (Exception e) {
            log.error("Error: {}, Product with ID {}", e.getMessage(), id);
            return null;
        }
    }

}
