package com.willyan.domain.mapper;

import org.mapstruct.Mapper;

import com.willyan.domain.Review;
import com.willyan.domain.message.ReviewMessage;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(final ReviewMessage reviewMessage);
}
