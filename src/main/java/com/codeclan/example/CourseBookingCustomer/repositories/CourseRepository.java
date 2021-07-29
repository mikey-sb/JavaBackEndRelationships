package com.codeclan.example.CourseBookingCustomer.repositories;

import com.codeclan.example.CourseBookingCustomer.models.Course;
import com.codeclan.example.CourseBookingCustomer.models.enums.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByRating(Rating rating);
    List<Course> findByBookingsCustomerId(Long id);

}
