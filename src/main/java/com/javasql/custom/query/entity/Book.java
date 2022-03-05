package com.javasql.custom.query.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(generator = "abc")
    @GenericGenerator(name = "abc", strategy = "increment")
    private Integer id;
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "year_of_publication")
    private Integer yearOfPublication;
    @Column(name = "book_type")
    private String bookType;

    public Book(){

    }

    public Book(Integer id, String name, String description, Integer yearOfPublication, String bookType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.yearOfPublication = yearOfPublication;
        this.bookType = bookType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
}
