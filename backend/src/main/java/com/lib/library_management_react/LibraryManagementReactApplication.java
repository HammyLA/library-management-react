package com.lib.library_management_react;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.model.Member;
import com.lib.library_management_react.model.Rental;
import com.lib.library_management_react.repository.BookRepository;
import com.lib.library_management_react.repository.MemberRepository;
import com.lib.library_management_react.repository.RentalRepository;

@SpringBootApplication
public class LibraryManagementReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementReactApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BookRepository books, MemberRepository members, RentalRepository rentals) {
		return args -> {
			AggregateReference<Member, Integer> lc = AggregateReference.to(members.save(new Member("Lawrence", "Cuenco")).getMemberid());
			AggregateReference<Book, Integer> book = AggregateReference.to(books.save(new Book("The Very Hungry Caterpillar", "It's about a hungry caterpillar.", 1969)).getBookid());
			rentals.save(new Rental(lc, book));
		};
	}
}
