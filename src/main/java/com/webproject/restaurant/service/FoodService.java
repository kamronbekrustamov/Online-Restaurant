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

    public Food updateFood(FoodRequest foodRequest) {
        if (foodRepository.existsById(foodRequest.getId())) {
            String imageNameToStore = imageStorageService.saveImage(foodRequest.getImage());
            Food food = new Food(
                    foodRequest.getId(),
                    foodRequest.getName(),
                    foodRequest.getDescription(),
                    foodRequest.getPrice(),
                    foodRequest.getCategory(),
                    foodRequest.getRank(),
                    imageNameToStore
            );
            return foodRepository.save(food);
        } else {
            throw new IllegalArgumentException("A Food with Id " + foodRequest.getId() + " does not exist");
        }
    }

    public Food deleteFood(Long id) {
        Optional<Food> optionalFood = foodRepository.findById(id);
        if (optionalFood.isPresent()) {
            foodRepository.deleteById(id);
            return optionalFood.get();
        } else {
            throw new IllegalArgumentException("A Food with Id " + id + " does not exist");
        }
    }
}
