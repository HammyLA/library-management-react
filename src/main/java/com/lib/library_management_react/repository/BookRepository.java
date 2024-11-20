package com.lib.library_management_react.repository;

import org.springframework.data.repository.CrudRepository;

import com.lib.library_management_react.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
