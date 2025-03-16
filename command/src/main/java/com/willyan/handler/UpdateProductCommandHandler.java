package com.willyan.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.willyan.domain.command.UpdateProductCommand;
import com.willyan.domain.enums.Event;
import com.willyan.domain.message.Message;
import com.willyan.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Component
public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand> {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;

    @Override
    @Transactional
    public void handle(UpdateProductCommand command) {
        var savedProduct = productRepository.findById(command.getId())
                .orElseThrow(IllegalArgumentException::new);

        var productToBeSaved = savedProduct.toBuilder()
                .imageUrl(command.getImageUrl())
                .name(command.getName())
                .value(command.getValue())
                .description(command.getDescription())
                .build();

        final var productUpdated = productRepository.save(productToBeSaved);
        kafkaTemplate.send("tp-query", new Message<>(Event.PRODUCT_UPDATED, productUpdated));

    }

}
