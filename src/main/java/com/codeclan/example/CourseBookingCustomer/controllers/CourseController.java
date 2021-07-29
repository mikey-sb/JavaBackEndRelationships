package com.codeclan.example.CourseBookingCustomer.controllers;

import com.codeclan.example.CourseBookingCustomer.models.Course;
import com.codeclan.example.CourseBookingCustomer.models.Customer;
import com.codeclan.example.CourseBookingCustomer.models.enums.Rating;
import com.codeclan.example.CourseBookingCustomer.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value="/courses")
    public ResponseEntity<List<Course>> findByQuery(
            @RequestParam(name="rating", required = false) Rating rating,
            @RequestParam(name="customers", required = false) Long customerId
    ){
        if(rating != null){
            return new ResponseEntity<>(courseRepository.findByRating(rating), HttpStatus.OK);
        } else if (customerId != null){
            return new ResponseEntity<>(courseRepository.findByBookingsCustomerId(customerId), HttpStatus.OK);
        }
        return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
    }




}
