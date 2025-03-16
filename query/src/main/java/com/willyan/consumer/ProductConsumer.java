package com.willyan.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willyan.domain.Product;
import com.willyan.domain.Review;
import com.willyan.domain.mapper.ReviewMapper;
import com.willyan.domain.message.Message;
import com.willyan.domain.message.ReviewMessage;
import com.willyan.service.ProductQueryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductConsumer {

    @Autowired
    private ProductQueryService productQueryService;

    @Autowired
    private ReviewMapper reviewMapper;

    @KafkaListener(topics = "tp-query")
    public void receive(final Message<?> message) {
        switch (message.getEvent()) {
            case PRODUCT_CREATED -> {
                final var product = new ObjectMapper().convertValue(message.getPayload(), Product.class);
                productQueryService.save(product);
            }

            case PRODUCT_UPDATED -> {
                final var product = new ObjectMapper().convertValue(message.getPayload(), Product.class);
                productQueryService.update(product);
            }

            case PRODUCT_DELETED -> {
                final Long productId =  new ObjectMapper().convertValue(message.getPayload(), Long.class);
                productQueryService.deleteById(productId);
            }

            case REVIEW_CREATED -> {
                final ReviewMessage reviewMessage = new ObjectMapper().convertValue(message.getPayload(), ReviewMessage.class);
                final Review review = reviewMapper.toReview(reviewMessage);
                productQueryService.addReview(reviewMessage.getProduct().getId(),review);
            } 

            default -> log.error("Invalid event: {}", message.getEvent());

        }
    }
}
