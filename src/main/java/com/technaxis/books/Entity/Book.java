package com.technaxis.books.Entity;

import com.technaxis.books.Entity.dto.BookRequest;
import com.technaxis.books.Entity.dto.BookResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity@Data
@Table(name = "books")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(length = 100)
    private String title;

    @Lob
    private String description;

    @Column(length = 100)
    private String author;

    @Column(unique = true, length = 20)
    private String isbn;

    private Integer printYear;
    private Boolean readAlready;
    private byte[] image;

    public Book(BookRequest bookDto, byte[] image) {
        this.title = bookDto.getTitle();
        this.description = bookDto.getDescription();
        this.author = bookDto.getAuthor();
        this.isbn = bookDto.getIsbn();
        this.printYear = bookDto.getPrintYear();
        readAlready = false;
        this.image = image;
    }

    public BookRequest getDto() {
        return new BookRequest(title,description,author,isbn,printYear);
    }

    public BookResponse getResponse() {
        String path = "http://localhost:8081/images/%s.jpg";
        return new BookResponse(title,description,author,isbn,printYear,
                String.format(path,isbn.replace("-","")));
    }
}
