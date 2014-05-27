/*
 * Copyright (c) 2014 Haulmont
 */

package com.sample.library.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import java.util.List;
import javax.persistence.ManyToMany;

@NamePattern("%s %s|firstName,lastName")
@Table(name = "LIBRARY_AUTHOR")
@Entity(name = "library$Author")
public class Author extends StandardEntity {
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "MIDDLE_NAME", length = 50)
    protected String middleName;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

}