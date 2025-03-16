package com.willyan.domain.message;

import com.willyan.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMessage {
    private Long id;
    private String userName;
    private Integer rating;

    private Product product;
}
