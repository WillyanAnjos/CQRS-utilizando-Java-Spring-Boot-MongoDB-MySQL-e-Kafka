package com.willyan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.willyan.bus.CommandBus;
import com.willyan.domain.command.CreateProductCommand;
import com.willyan.domain.command.DeleteProductCommand;
import com.willyan.domain.command.UpdateProductCommand;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductCommandController {

    @Autowired
    private CommandBus commandBus;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid CreateProductCommand createProductCommand) {
        commandBus.execute(createProductCommand);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateProductCommand updateProductCommand) {

        updateProductCommand.setId(id);
        commandBus.execute(updateProductCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        commandBus.execute(new DeleteProductCommand(id));
    }

}
