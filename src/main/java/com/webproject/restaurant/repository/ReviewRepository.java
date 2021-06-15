package com.webproject.restaurant.repository;

import com.webproject.restaurant.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
