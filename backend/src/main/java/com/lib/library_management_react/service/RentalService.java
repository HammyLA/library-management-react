package com.lib.library_management_react.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.library_management_react.model.Rental;
import com.lib.library_management_react.repository.RentalRepository;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getById(Integer rentalid) {
        return rentalRepository.findById(rentalid);
    }

    public Rental createRental(Rental rental) {
        rentalRepository.save(rental);
        return rental;
    }

    public Rental updateRental(Integer id, Rental rentalDetails) {
        Rental rental = rentalRepository.findById(id);
        if (rental != null) {
            rental.setMember(rentalDetails.getMember());
            rental.setBook(rentalDetails.getBook());
            rental.setDayOfRental(rentalDetails.getDayOfRental());
            rental.setDueDate(rentalDetails.getDueDate());
            rentalRepository.update(rental);
        }
        return rental;
    }

    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }
}
