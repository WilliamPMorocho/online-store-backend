package com.unir.products.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unir.products.model.pojo.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface ProductJpaRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	List<Product> findByName(String name);

	List<Product> findByDescription(String description);

	List<Product> findByPrice(Double price);

	List<Product> findByRating(Integer rating);

	List<Product> findByCategoryId(Integer categoryId);

	List<Product> findByImages(String images);

	List<Product> findByState(Integer state);

	List<Product> findByNameAndCountry(String name, String country);

	List<Product> findByNameAndDescription(String name, String description);

	List<Product> findByNameAndPrice(String name, Double price);

	List<Product> findByNameAndRating(String name, Integer rating);

	List<Product> findByNameAndCategoryId(String name, Integer categoryId);

	List<Product> findByNameAndImages(String name, String images);

	List<Product> findByNameAndState(String name, Integer state);

	List<Product> findByDescriptionAndPrice(String description, Double price);

	List<Product> findByDescriptionAndRating(String description, Integer rating);

	List<Product> findByDescriptionAndCategoryId(String description, Integer categoryId);

	List<Product> findByDescriptionAndImages(String description, String images);

	List<Product> findByDescriptionAndState(String description, Integer state);

	List<Product> findByPriceAndRating(Double price, Integer rating);

	List<Product> findByPriceAndCategoryId(Double price, Integer categoryId);

	List<Product> findByPriceAndImages(Double price, String images);

	List<Product> findByPriceAndState(Double price, Integer state);

	List<Product> findByRatingAndCategoryId(Integer rating, Integer categoryId);

	List<Product> findByRatingAndImages(Integer rating, String images);

	List<Product> findByRatingAndState(Integer rating, Integer state);

	List<Product> findByCategoryIdAndImages(Integer categoryId, String images);

	List<Product> findByCategoryIdAndState(Integer categoryId, Integer state);

	List<Product> findByImagesAndState(String images, Integer state);

}
