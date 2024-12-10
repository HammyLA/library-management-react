package com.lib.library_management_react.controller;

import com.lib.library_management_react.controller.dto.RentalDetails;
import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.model.Member;
import com.lib.library_management_react.model.Rental;
import com.lib.library_management_react.service.BookService;
import com.lib.library_management_react.service.MemberService;
import com.lib.library_management_react.service.RentalService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentalControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private MemberService memberService;

    @Mock
    private RentalService rentalService;

    @InjectMocks
    private RentalController rentalController;

    RentalControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Rental rental1 = new Rental(1, 1);
        Rental rental2 = new Rental(2, 2);
        when(rentalService.getAllRentals()).thenReturn(Arrays.asList(rental1, rental2));

        Iterable<Rental> rentals = rentalController.findAll();

        assertNotNull(rentals);
        verify(rentalService, times(1)).getAllRentals();
    }

    @Test
    void testFindById() {
        Rental rental = new Rental(1, 1);
        rental.setRentalid(1);
        when(rentalService.getById(1)).thenReturn(rental);

        ResponseEntity<Rental> response = rentalController.findById(1);

        assertEquals(ResponseEntity.ok(rental), response);
        verify(rentalService, times(1)).getById(1);
    }

    @Test
    void testFindByIdNotFound() {
        when(rentalService.getById(1)).thenReturn(null);

        ResponseEntity<Rental> response = rentalController.findById(1);

        assertEquals(ResponseEntity.status(404).build(), response);
        verify(rentalService, times(1)).getById(1);
    }

    @Test
    void testCheckOverdue() {
        when(rentalService.isOverdue(1)).thenReturn(true);

        Boolean isOverdue = rentalController.checkOverdue(1);

        assertTrue(isOverdue);
        verify(rentalService, times(1)).isOverdue(1);
    }

    @Test
    void testGetRentalDetails() {
        Rental rental = new Rental(1, 1);
        rental.setRentalid(1);
        Member member = new Member("John", "Doe", "john.doe@example.com");
        member.setMemberid(1);
        Book book = new Book("Test Book", "Test Author", "Test Genre", "Test Description", 1234567890);
        book.setBookid(1);

        when(rentalService.getById(1)).thenReturn(rental);
        when(memberService.getById(1)).thenReturn(member);
        when(bookService.getBookById(1)).thenReturn(book);

        ResponseEntity<RentalDetails> response = rentalController.getRentalDetails(1);

        assertEquals(ResponseEntity.ok(new RentalDetails(rental, member, book)), response);
        verify(rentalService, times(1)).getById(1);
        verify(memberService, times(1)).getById(1);
        verify(bookService, times(1)).getBookById(1);
    }

    @Test
    void testDeleteRental() {
        Rental rental = new Rental(1, 1);
        rental.setRentalid(1);

        when(rentalService.getById(1)).thenReturn(rental);
        doNothing().when(rentalService).deleteRental(1);

        ResponseEntity<?> response = rentalController.deleteRental(1);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(rentalService, times(1)).deleteRental(1);
    }

    @Test
    void testDeleteRentalNotFound() {
        when(rentalService.getById(1)).thenReturn(null);

        ResponseEntity<?> response = rentalController.deleteRental(1);

        assertEquals(ResponseEntity.status(404).body("Rental not found."), response);
        verify(rentalService, never()).deleteRental(any());
    }

    @Test
    void testPostRentalBookAlreadyRented() {
        Rental rental = new Rental(1, 1);
        when(bookService.isRented(1)).thenReturn(true);

        ResponseEntity<?> response = rentalController.postRental(rental);

        assertEquals(ResponseEntity.status(400).body("Rental creation failed: Book with ID 1 is already rented."),
                     response);
        verify(rentalService, never()).createRental(any());
    }

    @Test
    void testPostRentalSuccess() {
        Rental rental = new Rental(1, 1);
        when(bookService.isRented(1)).thenReturn(false);
        when(rentalService.createRental(rental)).thenReturn(rental);

        ResponseEntity<?> response = rentalController.postRental(rental);

        assertEquals(ResponseEntity.status(201).body(rental), response);
        verify(rentalService, times(1)).createRental(rental);
    }
}
