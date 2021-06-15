package com.webproject.restaurant.repository;

import com.webproject.restaurant.model.Booking;
import com.webproject.restaurant.model.User;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Iterable<Booking> getBookingsByUser(User user);
}
