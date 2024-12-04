package com.lib.library_management_react.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lib.library_management_react.model.Rental;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer> {
    
}
