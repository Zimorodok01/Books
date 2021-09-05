package com.technaxis.books.Service;

import com.technaxis.books.Entity.Book;
import com.technaxis.books.Entity.dto.BookRequest;
import com.technaxis.books.Entity.dto.BookResponse;
import com.technaxis.books.Repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Service @Slf4j
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final String BOOK_ISBN_ALREADY_EXISTS = "Book with isbn %s is already exists";
    private final String BOOK_DOES_NOT_EXIST = "Book with id %d doesn't exist";
    private final String INVALID_PAGE = "Page %d is invalid";
    private final String PATH = "src/main/resources/static/images/";

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookRequest createBook(BookRequest bookDto, MultipartFile image) throws IOException {
        String isbn = bookDto.getIsbn();
        if (bookRepository.findByIsbn(isbn) != null) {
            throwBadRequest(String.format(BOOK_ISBN_ALREADY_EXISTS,isbn));
        }

        File file = new File(PATH + isbn.replace("-","") + ".jpg");
        if (!file.exists()) {
            if (file.createNewFile()) {
                log.info("file: " + file.getName() + " is created");
            }
        }

        try {
            OutputStream os = new FileOutputStream(file);
            os.write(image.getBytes());
        } catch (Exception ex) {
            log.error("Something with output stream");
            throwBadRequest("Output stream is invalid");
        }

        Book book = new Book(bookDto,image.getBytes());
        log.info("Saving book " + book.getTitle());
        bookRepository.save(book);
        log.info("Book is saved");
        return book.getDto();
    }

    @Override
    public BookRequest updateBook(long id, BookRequest bookDto, byte[] image) throws IOException {
        Book book = bookRepository.findById(id);
        if (book == null) {
            throwBadRequest(String.format(BOOK_DOES_NOT_EXIST,id));
        }

        if (bookDto.getTitle() != null) {
            book.setTitle(bookDto.getTitle());
        }

        if (bookDto.getDescription() != null) {
            book.setDescription(bookDto.getDescription());
        }

        if (bookDto.getIsbn() != null) {
            File file = new File(PATH + book.getIsbn().replace("-","") + ".jpg");

            if (file.exists()) {
                if (file.delete()) log.info("File is deleted");
            }

            book.setIsbn(bookDto.getIsbn());
        }

        if (bookDto.getPrintYear() != null) {
            book.setPrintYear(bookDto.getPrintYear());
        }

        if (image.length != 0) {
            File file = new File(PATH + book.getIsbn().replace("-","") + ".jpg");
            if (file.createNewFile()) {
                log.info("File created");
            }

            OutputStream os = new FileOutputStream(file);
            os.write(image);

            book.setImage(image);
        }

        log.info("Updating book " + book.getTitle());
        bookRepository.save(book);
        log.info("Book is updated");
        return book.getDto();
    }

    @Override
    public String readBook(long id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            throwBadRequest(String.format(BOOK_DOES_NOT_EXIST,id));
        }

        if (book.getReadAlready()) {
            return "Book is already read";
        }

        book.setReadAlready(true);
        log.info("Book " + book.getTitle() + " is been reading");
        bookRepository.save(book);
        log.info("Book " + book.getTitle() + " is read");
        return "Book is read";
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> booksDto = new ArrayList<>();
        log.info("Fetching all books from database");
        books.forEach(book -> booksDto.add(book.getResponse()));
        log.info("Books are fetched");

        return booksDto;
    }

    @Override
    public List<BookResponse> getBooksByPage(int page) {
        List<Book> books = bookRepository.findAll();
        if ((page > Math.ceil((double) books.size() / 10)) || page < 1)
            throwBadRequest(String.format(INVALID_PAGE,page));
        List<BookResponse> booksDto = new ArrayList<>();
        for (int i = (page-1)*10; i < 10*page;i++) {
            if (i == books.size()) break;

            Book book = books.get(i);
            booksDto.add(book.getResponse());
        }

        return booksDto;
    }

    @Override
    public BookResponse getBook(Integer id) throws UnsupportedEncodingException {
        Book book = bookRepository.findById(id);
        if (book == null) {
            throwBadRequest(String.format(BOOK_DOES_NOT_EXIST,id));
        }

        return book.getResponse();
    }

    @Override
    public ResponseEntity<String> searchByPhrase(String phrase) {
        phrase = phrase.toLowerCase();
        List<Book> books = bookRepository.findAll();
        List<BookResponse> booksDto = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(phrase) ||
                book.getDescription().toLowerCase().contains(phrase)) {
                booksDto.add(book.getResponse());
            }
        }

        return new ResponseEntity<String>("We find " + booksDto.size() + " results. " +
                "If you want to see them. You should go to this link:" +
                " http://localhost:8081/api/v1/books/find/{pagenumber}",OK);
    }

    @Override
    public List<BookResponse> getBooksByPhrase(Integer page, String phrase) {
        phrase = phrase.toLowerCase();
        List<Book> books = bookRepository.findAll();
        List<BookResponse> booksWithPhrase = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(phrase) ||
                    book.getDescription().toLowerCase().contains(phrase)) {
                booksWithPhrase.add(book.getResponse());
            }
        }

        if ((page > Math.ceil((double) booksWithPhrase.size()/10)) || page < 1) {
            throwBadRequest(String.format(INVALID_PAGE,page));
        }

        List<BookResponse> booksDto = new ArrayList<>();
        for (int i = (page-1)*10; i < 10*page;i++) {
            if (i == books.size()) break;

            BookResponse book = booksWithPhrase.get(i);
            booksDto.add(book);
        }

        return booksDto;
    }

    private void throwBadRequest(String message) {
        throw new ResponseStatusException(BAD_REQUEST,message);
    }
}
