package com.webproject.restaurant.service;

import com.webproject.restaurant.dto.FoodRequest;
import com.webproject.restaurant.model.Food;
import com.webproject.restaurant.repository.FoodRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;
    private final ImageStorageService imageStorageService;

    public Iterable<Food> getFoods() {
        return foodRepository.findAll();
    }

    public Food getFood(Long id) {
        return foodRepository.findById(id).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("A Food with Id " + id + " does not exist");
                });
    }

    public Food saveFood(FoodRequest foodRequest) {

        String imageNameToStore = imageStorageService.saveImage(foodRequest.getImage());

        Food food = new Food(
                null,
                foodRequest.getName(),
                foodRequest.getDescription(),
                foodRequest.getPrice(),
                foodRequest.getCategory(),
                foodRequest.getRank(),
                imageNameToStore
                );

        return foodRepository.save(food);
    }

    public Food updateFood(FoodRequest foodRequest, Long id) {
        Optional<Food> optionalFood = foodRepository.findById(id);
        if (optionalFood.isPresent()) {
            String imageNameToStore =
                    imageStorageService.updateImage(optionalFood.get().getImageUrl(), foodRequest.getImage());
            Food food = new Food(
                    id,
                    foodRequest.getName(),
                    foodRequest.getDescription(),
                    foodRequest.getPrice(),
                    foodRequest.getCategory(),
                    foodRequest.getRank(),
                    imageNameToStore
            );
            return foodRepository.save(food);
        } else {
            throw new IllegalArgumentException("A Food with Id " + id + " does not exist");
        }
    }

    public Food deleteFood(Long id) {
        Optional<Food> optionalFood = foodRepository.findById(id);
        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();
            imageStorageService.deleteImage(food.getImageUrl());
            foodRepository.deleteById(id);
            return food;
        } else {
            throw new IllegalArgumentException("A Food with Id " + id + " does not exist");
        }
    }
}
