package com.webproject.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webproject.restaurant.model.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "message")
    private String message;

    @Column(name = "status")
    private BookingStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getUsername() {
        return user.getFirstName() + " " + user.getLastName();
    }
}
