package com.technaxis.books.Entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse implements Serializable {
    private String title;
    private String description;
    private String author;
    private String isbn;
    private Integer printYear;
    private String imageURL;
}
