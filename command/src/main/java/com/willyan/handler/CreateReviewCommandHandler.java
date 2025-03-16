package com.willyan.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.willyan.domain.Review;
import com.willyan.domain.command.CreateReviewCommand;
import com.willyan.domain.enums.Event;
import com.willyan.domain.message.Message;
import com.willyan.repository.ProductRepository;
import com.willyan.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Component
public class CreateReviewCommandHandler implements CommandHandler<CreateReviewCommand> {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;

    @Override
    @Transactional
    public void handle(CreateReviewCommand command) {

        final var product = productRepository.findById(command.getProductId()).orElseThrow(IllegalArgumentException::new);

        final var review = Review.builder()
                .userName(command.getUserName())
                .rating(command.getRating())
                .product(product)
                .build();

        final var savedReview = reviewRepository.save(review);
        kafkaTemplate.send("tp-query", new Message<>(Event.REVIEW_CREATED, savedReview));
    }

}
