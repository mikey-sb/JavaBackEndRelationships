package com.codeclan.example.CourseBookingCustomer.controllers;

import com.codeclan.example.CourseBookingCustomer.models.Booking;
import com.codeclan.example.CourseBookingCustomer.models.Course;
import com.codeclan.example.CourseBookingCustomer.models.Customer;
import com.codeclan.example.CourseBookingCustomer.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @GetMapping(value="/bookings")
    public ResponseEntity<List<Booking>> findAll(
            @RequestParam(name = "date", required = false) String date
    ){
        if(date != null){
            return new ResponseEntity<>(bookingRepository.findByDate(date), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/bookings/{id}")
    public Optional<Booking> getBooking(@PathVariable Long id){
        return bookingRepository.findById(id);
    }

    @PutMapping(value = "/bookings/{id}")
    Booking replaceBooking(@RequestBody Booking newBooking, @PathVariable Long id){
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setDate(newBooking.getDate());
                    return bookingRepository.save(booking);
                })
                .orElseGet(() -> {
                    newBooking.setId(id);
                    return bookingRepository.save(newBooking);
                });
    }
}
