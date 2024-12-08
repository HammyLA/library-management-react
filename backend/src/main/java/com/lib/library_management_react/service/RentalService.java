package com.lib.library_management_react.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.library_management_react.model.Rental;
import com.lib.library_management_react.repository.RentalRepository;

/**
 * Service layer for managing Rental-related operations.
 * Handles business logic and acts as an intermediary between controllers and the repository layer.
 */
@Service
public class RentalService {

    @Autowired // Automatically injects the RentalRepository bean.
    private RentalRepository rentalRepository;

    /**
     * Retrieves all rentals from the database.
     *
     * @return a list of all rentals.
     */
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    /**
     * Retrieves all overdue rentals.
     * A rental is considered overdue if its due date has passed.
     *
     * @return a list of overdue rentals.
     */
    public List<Rental> getAllRentalsOverdue() {
        return rentalRepository.findAllOverdue();
    }

    /**
     * Checks if a specific rental is overdue.
     *
     * @param id the ID of the rental to check.
     * @return true if the rental is overdue, false otherwise.
     */
    public Boolean isOverdue(Integer id) {
        return rentalRepository.checkOverdue(id) != null; // If checkOverdue returns a result, it's overdue.
    }

    /**
     * Retrieves a specific rental by its ID.
     *
     * @param rentalid the ID of the rental to retrieve.
     * @return the Rental object, or null if not found.
     */
    public Rental getById(Integer rentalid) {
        return rentalRepository.findById(rentalid);
    }

    /**
     * Creates a new rental and saves it to the database.
     *
     * @param rental the Rental object containing details of the new rental.
     * @return the created Rental object.
     */
    public Rental createRental(Rental rental) {
        rentalRepository.save(rental);
        return rental;
    }

    /**
     * Updates the details of an existing rental.
     * If the rental is not found, returns null.
     *
     * @param id           the ID of the rental to update.
     * @param rentalDetails the updated details of the rental.
     * @return the updated Rental object, or null if not found.
     */
    public Rental updateRental(Integer id, Rental rentalDetails) {
        Rental rental = rentalRepository.findById(id); // Retrieve the existing rental.
        if (rental != null) { // Update only if the rental exists.
            rental.setMember(rentalDetails.getMember());
            rental.setBook(rentalDetails.getBook());
            rental.setDayOfRental(rentalDetails.getDayOfRental());
            rental.setDueDate(rentalDetails.getDueDate());
            rentalRepository.update(rental); // Save the updated rental to the database.
        }
        return rental;
    }

    /**
     * Deletes a rental by its ID.
     *
     * @param id the ID of the rental to delete.
     */
    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }
}
