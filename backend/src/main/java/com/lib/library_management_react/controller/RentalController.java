package com.lib.library_management_react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.library_management_react.controller.dto.RentalDetails;
import com.lib.library_management_react.model.Rental;
import com.lib.library_management_react.repository.BookRepository;
import com.lib.library_management_react.repository.MemberRepository;
import com.lib.library_management_react.repository.RentalRepository;


@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RentalController {
    
    @Autowired
    private final BookRepository books;
    @Autowired
    private final MemberRepository members;
    @Autowired
    private final RentalRepository rentals;

    public RentalController(BookRepository books, MemberRepository members, RentalRepository rentals) {
        this.books = books;
        this.members = members;
        this.rentals = rentals;
    }

    @GetMapping
    public Iterable<Rental> findAll() {
        return rentals.findAll();
    }
    

    @GetMapping("/{id}")
    public Rental findById(@PathVariable Integer id) {
        return rentals.findById(id).orElse(null);
    }

    @GetMapping("/{id}/details")
    public RentalDetails getRentalDetails(@PathVariable Integer id) {
        Rental rental = rentals.findById(id).orElse(null);
        return new RentalDetails(rental, members.findById(rental.getMember().getId()).get(), books.findById(rental.getBook().getId()).get());
    }

}