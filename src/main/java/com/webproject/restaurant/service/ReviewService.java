package com.webproject.restaurant.service;

import com.webproject.restaurant.model.Review;
import com.webproject.restaurant.model.User;
import com.webproject.restaurant.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Iterable<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public Review saveReview(String review) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review newReview = new Review(
                null,
                review,
                LocalDateTime.now(),
                user
        );
        reviewRepository.save(newReview);
        return newReview;
    }

    public Review deleteReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            reviewRepository.deleteById(id);
            return reviewOptional.get();
        } else {
            throw new IllegalArgumentException("Review with id " + id + " does not exist");
        }
    }
}
