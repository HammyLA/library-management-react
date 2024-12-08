package com.lib.library_management_react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lib.library_management_react.controller.dto.RentalDetails;
import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.model.Member;
import com.lib.library_management_react.model.Rental;
import com.lib.library_management_react.service.BookService;
import com.lib.library_management_react.service.MemberService;
import com.lib.library_management_react.service.RentalService;

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

    /**
     * GET /api/rentals: Retrieve all rentals.
     *
     * @return a list of all rentals.
     */
    @GetMapping
    public Iterable<Rental> findAll() {
        return rentals.getAllRentals();
    }

    /**
     * GET /api/rentals/overdue: Retrieve all overdue rentals.
     *
     * @return a list of all overdue rentals.
     */
    @GetMapping("/overdue")
    public Iterable<Rental> findAllOverdue() {
        return rentals.getAllRentalsOverdue();
    }

    /**
     * GET /api/rentals/{id}: Retrieve a rental by its ID.
     *
     * @param id the rental ID.
     * @return the requested rental or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Rental> findById(@PathVariable Integer id) {
        Rental rental = rentals.getById(id);
        if (rental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(rental);
    }

    /**
     * GET /api/rentals/{id}/overdue: Check if a rental is overdue.
     *
     * @param id the rental ID.
     * @return true if the rental is overdue, false otherwise.
     */
    @GetMapping("/{id}/overdue")
    public Boolean checkOverdue(@PathVariable Integer id) {
        return rentals.isOverdue(id);
    }

    /**
     * GET /api/rentals/{id}/details: Retrieve detailed information about a rental.
     *
     * @param id the rental ID.
     * @return the rental details including member and book information.
     */
    @GetMapping("/{id}/details")
    public ResponseEntity<RentalDetails> getRentalDetails(@PathVariable Integer id) {
        Rental rental = rentals.getById(id);
        if (rental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if rental not found
        }
        
        Member member = members.getById(rental.getMember());
        Book book = books.getBookById(rental.getBook());

        if (member == null || book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if member or book not found
        }

        return ResponseEntity.ok(new RentalDetails(rental, member, book));
    }

    /**
     * DELETE /api/rentals/{id}: Delete a rental by its ID.
     *
     * @param id the rental ID.
     * @return HTTP 204 if successful, HTTP 404 if rental not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRental(@PathVariable Integer id) {
        Rental rental = rentals.getById(id);
        if (rental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental not found.");
        }

        rentals.deleteRental(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return 204 No Content on successful deletion
    }

    /**
     * POST /api/rentals: Create a new rental if the book is available.
     *
     * @param rental the rental object to be created.
     * @return the created rental or an error message if the book is already rented.
     */
    @PostMapping
    public ResponseEntity<?> postRental(@RequestBody Rental rental) {
        if (books.isRented(rental.getBook())) {
            String errorMessage = "Rental creation failed: Book with ID " + rental.getBook() + " is already rented.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        Rental savedRental = rentals.createRental(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
    }
}
