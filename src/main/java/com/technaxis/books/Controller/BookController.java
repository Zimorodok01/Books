package com.technaxis.books.Controller;

import com.technaxis.books.Entity.dto.BookRequest;
import com.technaxis.books.Entity.dto.BookResponse;
import com.technaxis.books.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public BookRequest createBook(@ModelAttribute BookRequest bookDto,
                                  @RequestPart("image") MultipartFile image) throws IOException {
        return bookService.createBook(bookDto,image);
    }

    @PutMapping("/{bookId}")
    public BookRequest updateBook(
            @PathVariable("bookId") Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer printYear,
            @RequestPart(required = false) MultipartFile image) throws IOException {
        BookRequest bookDto = new BookRequest(title,description,isbn,printYear);

        if (image == null) {
            return bookService.updateBook(id,bookDto,null);
        }

        return bookService.updateBook(id,bookDto,image.getBytes());
    }

    @GetMapping("/{bookId}/read")
    public String readBook(@PathVariable("bookId") long id) {
        return bookService.readBook(id);
    }

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/page/{pageNumber}")
    public List<BookResponse> getBooksByPage(@PathVariable("pageNumber") int page) {
        return bookService.getBooksByPage(page);
    }

    @GetMapping("/{bookId}")
    public BookResponse getBook(@PathVariable("bookId") Integer id) throws UnsupportedEncodingException {
        return bookService.getBook(id);
    }

    @GetMapping("/find")
    public ResponseEntity<String> searchByPhrase(@RequestParam String phrase) {
        return bookService.searchByPhrase(phrase);
    }

    @GetMapping("/find/{pageNumber}")
    public List<BookResponse> getBooksByPhrase(
            @PathVariable("pageNumber") Integer page,
            @RequestParam String phrase) {
        return bookService.getBooksByPhrase(page,phrase);
    }

}
