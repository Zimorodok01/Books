package com.technaxis.books.Service;

import com.technaxis.books.Entity.dto.BookRequest;
import com.technaxis.books.Entity.dto.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface BookService {
    BookRequest createBook(BookRequest bookDto, MultipartFile image) throws IOException;

    BookRequest updateBook(long id, BookRequest bookDto, byte[] image) throws IOException;

    String readBook(long id);

    List<BookResponse> getAllBooks();

    List<BookResponse> getBooksByPage(int page);

    BookResponse getBook(Integer id) throws UnsupportedEncodingException;

    ResponseEntity<String> searchByPhrase(String phrase);

    List<BookResponse> getBooksByPhrase(Integer page, String phrase);
}
