package com.codeclan.example.CourseBookingCustomer.repositories;

import com.codeclan.example.CourseBookingCustomer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByBookingsCourseId(Long id);
}
