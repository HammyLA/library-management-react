package com.lib.library_management_react;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.repository.BookRepository;

@SpringBootApplication
public class LibraryManagementReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementReactApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BookRepository books) {
		return args -> {
			books.save(new Book("The Cat In The Hat", "It's the Dr. Seuss Book about a cat in a hat and he rhymes :D", 1957));
		};
	}
}
