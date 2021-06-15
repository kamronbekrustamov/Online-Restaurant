package com.webproject.restaurant.dto;

import com.webproject.restaurant.model.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class BookingRequest {


    private Long id;

    @NotNull(message = "Number of People is required")
    private Integer numberOfPeople;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date cannot be at past")
    private LocalDate date;

    @NotNull(message = "Time is required")
    private String time;

    private String message;

    private BookingStatus status;

}
