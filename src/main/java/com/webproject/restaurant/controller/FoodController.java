package com.webproject.restaurant.controller;

import com.webproject.restaurant.dto.FoodRequest;
import com.webproject.restaurant.model.Food;
import com.webproject.restaurant.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/foods")
@AllArgsConstructor
@Slf4j
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public Iterable<Food> getFoods() {
        return foodService.getFoods();
    }

    @GetMapping("/{id}")
    public Food getFood(@PathVariable Long id) {
        return foodService.getFood(id);
    }

    @PostMapping
    public Food saveFood(@Valid @ModelAttribute FoodRequest foodRequest) {
        return foodService.saveFood(foodRequest);
    }

    @PutMapping
    public Food updateFood(@Valid @ModelAttribute FoodRequest foodRequest) {
        return foodService.updateFood(foodRequest);
    }

    @DeleteMapping("/{id}")
    public Food deleteFood(@PathVariable Long id) {
        return foodService.deleteFood(id);
    }
}
