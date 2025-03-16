package com.willyan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willyan.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
