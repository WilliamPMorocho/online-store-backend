package com.unir.products.data;

import com.unir.products.data.utils.SearchCriteria;
import com.unir.products.data.utils.SearchOperation;
import com.unir.products.data.utils.SearchStatement;
import com.unir.products.model.pojo.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductJpaRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

    public List<Product> search(String name, String description, Double price, Integer rating, Integer categoryId, String images, Integer state, Integer stock) {
        SearchCriteria<Product> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(name)) {
            spec.add(new SearchStatement("name", name, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(description)) {
            spec.add(new SearchStatement("description", description, SearchOperation.MATCH));
        }
        if (price != null) {
            spec.add(new SearchStatement("price", price, SearchOperation.LESS_THAN_EQUAL));
        }
        if (rating != null) {
            spec.add(new SearchStatement("rating", rating, SearchOperation.EQUAL));
        }
        if (categoryId != null) {
            spec.add(new SearchStatement("category_id", categoryId, SearchOperation.EQUAL));
        }
        if (StringUtils.isNotBlank(images)) {
            spec.add(new SearchStatement("images", images, SearchOperation.MATCH));
        }
        if (state != null) {
            spec.add(new SearchStatement("state", state, SearchOperation.EQUAL));
        }
        if (stock != null) {
            spec.add(new SearchStatement("stock", stock, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);
    }
}
