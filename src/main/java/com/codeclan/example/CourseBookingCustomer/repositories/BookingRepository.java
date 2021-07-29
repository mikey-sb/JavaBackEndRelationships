package com.codeclan.example.CourseBookingCustomer.repositories;

import com.codeclan.example.CourseBookingCustomer.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>{

    List<Booking> findByDate (String date);
}
