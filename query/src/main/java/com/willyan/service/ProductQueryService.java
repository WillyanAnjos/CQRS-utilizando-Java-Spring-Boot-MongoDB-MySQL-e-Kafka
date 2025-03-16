package com.willyan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willyan.domain.Product;
import com.willyan.domain.Review;
import com.willyan.repository.ProductRepository;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void update(final Product product) {
        var savedProduct = findById(product.getId());
        var productToBeSaved = savedProduct.toBuilder()
                .imageUrl(product.getImageUrl())
                .name(product.getName())
                .value(product.getValue())
                .description(product.getDescription())
                .build();

        productRepository.save(productToBeSaved);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void addReview(Long id, Review review) {
        Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        product.getReviews().add(review);
        productRepository.save(product);
    }
}
