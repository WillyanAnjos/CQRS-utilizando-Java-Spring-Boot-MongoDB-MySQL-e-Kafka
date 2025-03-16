package com.willyan.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.willyan.domain.command.DeleteProductCommand;
import com.willyan.domain.enums.Event;
import com.willyan.domain.message.Message;
import com.willyan.repository.ProductRepository;

import jakarta.transaction.Transactional;

public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;


    @Override
    @Transactional
    public void handle(DeleteProductCommand command) {

        productRepository.findById(command.getId())
                .orElseThrow(IllegalArgumentException::new);

        productRepository.deleteById(command.getId());

        kafkaTemplate.send("tp-query", new Message<>(Event.PRODUCT_DELETED, command.getId()));
    }

}
