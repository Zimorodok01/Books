package com.technaxis.books.Entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest implements Serializable {
    private String title;
    private String description;
    private String author;
    private String isbn;
    private Integer printYear;

    public BookRequest(String title, String description, String isbn, Integer printYear) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.printYear = printYear;
    }
}
