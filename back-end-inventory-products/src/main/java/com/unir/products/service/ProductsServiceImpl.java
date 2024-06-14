package com.unir.products.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.products.data.ProductRepository;
import com.unir.products.model.pojo.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.products.model.pojo.Product;
import com.unir.products.model.request.CreateProductRequest;

@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ObjectMapper objectMapper;


	@Override
	public List<Product> getProducts(String name, String description, Double price, Integer rating, Integer categoryId, String images, Integer state, Integer stock) {
		if (StringUtils.hasLength(name) ||
				StringUtils.hasLength(description) ||
				price != null ||
				rating != null ||
				categoryId != null ||
				StringUtils.hasLength(images) ||
				state != null ||
				stock != null) {
			return repository.search(name, description, price, rating, categoryId, images, state, stock);
		}
		List<Product> products = repository.getProducts();
		return products.isEmpty() ? null : products;
	}


	@Override
	public Product getProduct(String productId) {
		return repository.getById(Long.valueOf(productId));
	}

	@Override
	public Boolean removeProduct(String productId) {

		Product product = repository.getById(Long.valueOf(productId));

		if (product != null) {
			repository.delete(product);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Product createProduct(CreateProductRequest request) {

		// Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
		if (request != null &&
				StringUtils.hasLength(request.getName().trim()) &&
				StringUtils.hasLength(request.getDescription().trim()) &&
				request.getPrice() > 0 &&
				request.getRating() >= 0 &&
				request.getCategoryId() > 0 &&
				StringUtils.hasLength(request.getImages().trim()) &&
				request.getState() >= 0 &&
				request.getStock() >= 0) {

			Product product = Product.builder()
					.name(request.getName())
					.description(request.getDescription())
					.price(request.getPrice())
					.rating(request.getRating())
					.categoryId(request.getCategoryId())
					.images(request.getImages())
					.state(request.getState())
					.stock(request.getStock())
					.build();

			return repository.save(product);
		} else {
			return null;
		}
	}

	@Override
	public Product updateProduct(String productId, String request) {

		//PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
		Product product = repository.getById(Long.valueOf(productId));
		if (product != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(product)));
				Product patched = objectMapper.treeToValue(target, Product.class);
				repository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating product {}", productId, e);
                return null;
            }
        } else {
			return null;
		}
	}

	@Override
	public Product updateProduct(String productId, ProductDto updateRequest) {
		Product product = repository.getById(Long.valueOf(productId));
		if (product != null) {
			product.update(updateRequest);
			repository.save(product);
			return product;
		} else {
			return null;
		}
	}

}
