package com.codeclan.example.CourseBookingCustomer.controllers;

import com.codeclan.example.CourseBookingCustomer.models.Course;
import com.codeclan.example.CourseBookingCustomer.models.Customer;
import com.codeclan.example.CourseBookingCustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;


    @GetMapping(value="/customers")
    public ResponseEntity<List<Customer>> findAll(
            @RequestParam(name="course", required = false) Long id
    ){
        if(id != null){
            return new ResponseEntity<>(customerRepository.findByBookingsCourseId(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }


}
