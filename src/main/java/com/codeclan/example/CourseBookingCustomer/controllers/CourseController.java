package com.codeclan.example.CourseBookingCustomer.controllers;

import com.codeclan.example.CourseBookingCustomer.models.Course;
import com.codeclan.example.CourseBookingCustomer.models.Customer;
import com.codeclan.example.CourseBookingCustomer.models.enums.Rating;
import com.codeclan.example.CourseBookingCustomer.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value = "/courses")
    public ResponseEntity<List<Course>> findByQuery(
            @RequestParam(name = "rating", required = false) Rating rating,
            @RequestParam(name = "customers", required = false) Long customerId
    ) {
        if (rating != null) {
            return new ResponseEntity<>(courseRepository.findByRating(rating), HttpStatus.OK);
        } else if (customerId != null) {
            return new ResponseEntity<>(courseRepository.findByBookingsCustomerId(customerId), HttpStatus.OK);
        }
        return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/courses/{id}")
    public Optional<Course> getCourse(@PathVariable Long id){
        return courseRepository.findById(id);
    }

    @PutMapping(value = "/courses/{id}")
    Course replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setTown(newCourse.getTown());
                    course.setRating(newCourse.getRating());
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return courseRepository.save(newCourse);
                });
    }

    @DeleteMapping(value = "/courses/{id}")
    void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }
}




