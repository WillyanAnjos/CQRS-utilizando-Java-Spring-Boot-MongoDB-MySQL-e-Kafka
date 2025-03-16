package com.willyan.domain.mapper;

import com.willyan.domain.Review;
import com.willyan.domain.message.ReviewMessage;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-16T12:46:27-0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review toReview(ReviewMessage reviewMessage) {
        if ( reviewMessage == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        review.id( reviewMessage.getId() );
        review.rating( reviewMessage.getRating() );
        review.userName( reviewMessage.getUserName() );

        return review.build();
    }
}
