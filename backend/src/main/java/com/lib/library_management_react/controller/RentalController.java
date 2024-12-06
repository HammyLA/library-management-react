package com.lib.library_management_react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.library_management_react.controller.dto.RentalDetails;
import com.lib.library_management_react.model.Rental;
import com.lib.library_management_react.repository.RentalRepository;
import com.lib.library_management_react.service.BookService;
import com.lib.library_management_react.service.MemberService;
import com.lib.library_management_react.service.RentalService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RentalController {

    @Autowired
    private BookService books;
    @Autowired
    private MemberService members;
    @Autowired
    private RentalService rentals;

    public RentalController(BookService books, MemberService members, RentalService rentals) {
        this.books = books;
        this.members = members;
        this.rentals = rentals;
    }

    @GetMapping
    public Iterable<Rental> findAll() {
        return rentals.getAllRentals();
    }

    @GetMapping("/{id}")
    public Rental findById(@PathVariable Integer id) {
        return rentals.getById(id);
    }

    @GetMapping("/{id}/details")
    public RentalDetails getRentalDetails(@PathVariable Integer id) {
        Rental rental = rentals.getById(id);
        return new RentalDetails(rental, members.getById(rental.getMember().getId()),
                books.getBookById(rental.getBook().getId()));
    }

    @PostMapping
    public ResponseEntity<Rental> postRental(@RequestBody Rental rental) {
        Rental savedRental = rentals.createRental(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
    }

}