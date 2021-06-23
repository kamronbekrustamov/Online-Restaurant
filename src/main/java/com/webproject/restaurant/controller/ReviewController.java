package com.webproject.restaurant.controller;

import com.webproject.restaurant.dto.ReviewRequest;
import com.webproject.restaurant.model.Review;
import com.webproject.restaurant.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public Iterable<Review> getReviews() {
        return reviewService.getReviews();
    }

    @PostMapping
    public Review saveReview(@Valid @RequestBody ReviewRequest reviewRequest) {
        return reviewService.saveReview(reviewRequest.getReview());
    }

    @DeleteMapping("/{id}")
    public Review deleteReview(@PathVariable Long id) {
        return reviewService.deleteReview(id);
    }
}
