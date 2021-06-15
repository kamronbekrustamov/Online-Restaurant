package com.webproject.restaurant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequest {
    @NotNull
    @Size(min = 1, max = 255, message = "Length must be between 1 and 255 characters")
    private String review;
}
