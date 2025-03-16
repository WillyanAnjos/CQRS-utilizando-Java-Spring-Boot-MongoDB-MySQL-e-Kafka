package com.willyan.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.willyan.domain.Product;
import com.willyan.domain.command.CreateProductCommand;
import com.willyan.domain.enums.Event;
import com.willyan.domain.message.Message;
import com.willyan.repository.ProductRepository;

import jakarta.transaction.Transactional;


@Component
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand> {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;

    @Override
    @Transactional
    public void handle(CreateProductCommand command) {

        final var product = Product.builder()
                .imageUrl(command.getImageUrl())
                .name(command.getName())
                .description(command.getDescription())
                .value(command.getValue())
                .build();

        final var savedProduct = repository.save(product);
        kafkaTemplate.send("tp-query", new Message<>(Event.PRODUCT_CREATED, savedProduct));
    }

}
