package com.webproject.restaurant.controller;

import com.webproject.restaurant.dto.BookingRequest;
import com.webproject.restaurant.model.Booking;
import com.webproject.restaurant.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public Iterable<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping
    public Booking saveBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        return bookingService.saveBooking(bookingRequest);
    }

    @PutMapping
    public Booking updateBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        return bookingService.updateBooking(bookingRequest);
    }

    @DeleteMapping("/{id}")
    public Booking deleteBooking(@PathVariable Long id) {
        return bookingService.deleteBooking(id);
    }
}
