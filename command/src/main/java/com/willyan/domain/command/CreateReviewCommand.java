package com.willyan.domain.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewCommand implements Command{

    @NotBlank
    private String userName;

    @NotNull
    private Integer rating;

    @NotNull
    private Long productId;
}
