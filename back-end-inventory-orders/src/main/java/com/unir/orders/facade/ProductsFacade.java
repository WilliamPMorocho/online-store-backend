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

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductsFacade {

    @Value("${getProduct.url}")
    private String getProductUrl;

    private final RestTemplate restTemplate;
    private final WebClient webClient;

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
        String url = "http://localhost:8088/products/" + id;
        return webClient.patch().uri(url)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Product.class);
    }

//    public Product patchProduct(String id, String body) {
//        try {
//            String url = String.format(getProductUrl, id);
//            log.info("Getting product with ID {}. Request to {}", id, url);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> reStringHttpEntity = new HttpEntity<>(body, headers);
//            return restTemplate.exchange(url, HttpMethod.PATCH, reStringHttpEntity, Product.class).getBody();
//        } catch (HttpClientErrorException e) {
//            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
//            return null;
//        } catch (HttpServerErrorException e) {
//            log.error("Server Error: {}, Product with ID {}", e.getStatusCode(), id);
//            return null;
//        } catch (Exception e) {
//            log.error("Error: {}, Product with ID {}", e.getMessage(), id);
//            return null;
//        }
//    }

}
