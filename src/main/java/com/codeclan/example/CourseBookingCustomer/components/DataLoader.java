package com.codeclan.example.CourseBookingCustomer.components;


import com.codeclan.example.CourseBookingCustomer.models.Booking;
import com.codeclan.example.CourseBookingCustomer.models.Course;
import com.codeclan.example.CourseBookingCustomer.models.Customer;
import com.codeclan.example.CourseBookingCustomer.models.enums.Rating;
import com.codeclan.example.CourseBookingCustomer.repositories.BookingRepository;
import com.codeclan.example.CourseBookingCustomer.repositories.CourseRepository;
import com.codeclan.example.CourseBookingCustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component

public class DataLoader implements ApplicationRunner {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CustomerRepository customerRepository;

    public DataLoader(){}

    public void run(ApplicationArguments args) {

        Customer bob = new Customer("Bob", "Glasgow", 40);
        customerRepository.save(bob);

        Customer jim = new Customer("Jim", "Edinburgh", 41);
        customerRepository.save(jim);

        Customer steve = new Customer("Steve", "Aberdeen", 39);
        customerRepository.save(steve);

        Course codeClan = new Course("CodeClan", "Glasgow", Rating.FIVE);
        courseRepository.save(codeClan);

        Course hydro = new Course("Hydroponics", "Edinburgh", Rating.FOUR);
        courseRepository.save(hydro);

        Course micro = new Course("Microgreen Essentials", "Aberdeen", Rating.THREE);
        courseRepository.save(micro);

        Booking booking2 = new Booking("10-02-2012", codeClan, jim);
        bookingRepository.save(booking2);

        Booking booking3 = new Booking("02-10-2015", codeClan, steve);
        bookingRepository.save(booking3);

        Booking booking4 = new Booking("01-09-2016", hydro, bob);
        bookingRepository.save(booking4);

        Booking booking6 = new Booking("01-11-2015", hydro, steve);
        bookingRepository.save(booking6);

        Booking booking7 = new Booking("01-09-2016", micro, bob);
        bookingRepository.save(booking7);

        Booking booking8 = new Booking("01-11-2015", micro, jim);
        bookingRepository.save(booking8);

    }
}
