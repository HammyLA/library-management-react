package com.lib.library_management_react.controller.dto;

import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.model.Member;
import com.lib.library_management_react.model.Rental;

public record RentalDetails(Rental rental, Member member, Book book) {
    
}
