package com.codeclan.example.CourseBookingCustomer.controllers;

import com.codeclan.example.CourseBookingCustomer.models.Course;
import com.codeclan.example.CourseBookingCustomer.models.Customer;
import com.codeclan.example.CourseBookingCustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;


    @GetMapping(value="/customers")
    public ResponseEntity<List<Customer>> findAll(
            @RequestParam(name="course", required = false) Long id,
            @RequestParam(name="course-town", required = false) String town,
            @RequestParam(name="age", required = false) Integer age
    ){
        if(id != null){
            return new ResponseEntity<>(customerRepository.findByBookingsCourseId(id), HttpStatus.OK);
        } else if (town != null && age == null){
            return new ResponseEntity<>(customerRepository.findByBookingsCourseTownIgnoreCase(town), HttpStatus.OK);
        } else if (age != null && town != null){
            return new ResponseEntity<>(customerRepository.findByBookingsCourseTownIgnoreCaseAndAgeGreaterThan(town, age), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/customers/{id}")
    public Optional<Customer> getCustomer(@PathVariable Long id){
        return customerRepository.findById(id);
    }

    @PostMapping(value="/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping(value = "/customers/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id){
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setTown(newCustomer.getTown());
                    customer.setAge(newCustomer.getAge());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }




}
