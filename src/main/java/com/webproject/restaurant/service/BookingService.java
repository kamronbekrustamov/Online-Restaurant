package com.webproject.restaurant.service;

import com.webproject.restaurant.dto.BookingRequest;
import com.webproject.restaurant.model.Booking;
import com.webproject.restaurant.model.User;
import com.webproject.restaurant.model.enums.BookingStatus;
import com.webproject.restaurant.model.enums.UserRole;
import com.webproject.restaurant.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Iterable<Booking> getAllBookings() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getUserRole().equals(UserRole.ROLE_USER)) {
            return bookingRepository.getBookingsByUser(user);
        } else {
            return bookingRepository.findAll();
        }
    }

    public Booking saveBooking(BookingRequest bookingRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Booking newBooking = new Booking(
                null,
                bookingRequest.getNumberOfPeople(),
                bookingRequest.getDate(),
                bookingRequest.getTime(),
                bookingRequest.getMessage(),
                BookingStatus.PROCESSING,
                user
        );

        return bookingRepository.save(newBooking);
    }

    public Booking updateBooking(BookingRequest bookingRequest) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingRequest.getId());
        if (optionalBooking.isPresent()) {
            Booking currentBooking = optionalBooking.get();
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (currentUser.getUserRole().equals(UserRole.ROLE_ADMIN)) {
                currentBooking.setStatus(bookingRequest.getStatus());
            } else {
                currentBooking.setNumberOfPeople(bookingRequest.getNumberOfPeople());
                currentBooking.setDate(bookingRequest.getDate());
                currentBooking.setTime(bookingRequest.getTime());
                currentBooking.setMessage(bookingRequest.getMessage());
            }
            return currentBooking;
        } else {
            throw new IllegalArgumentException("Booking with id " + bookingRequest.getId() + " does not exist");
        }
    }

    public Booking deleteBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if(optionalBooking.isPresent()) {
            bookingRepository.deleteById(id);
            return optionalBooking.get();
        } else {
            throw new IllegalArgumentException("Booking with id " + id + " does not exist");
        }
    }
}
