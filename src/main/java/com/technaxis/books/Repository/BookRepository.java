package com.technaxis.books.Repository;

import com.technaxis.books.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByIsbn(String isbn);

    Book findById(long id);
}
