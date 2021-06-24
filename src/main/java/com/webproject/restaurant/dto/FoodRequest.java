package com.webproject.restaurant.dto;

import com.webproject.restaurant.model.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FoodRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Category is required")
    private Category category;

    @NotNull(message = "Rank is required")
    private Integer rank;

    @NotNull(message = "Image is required")
    private MultipartFile image;
}
