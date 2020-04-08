package com.alone.booksalone.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Book {
    private int id;
    private String name;
    private String author;
    private String price;
    private int status;
}
