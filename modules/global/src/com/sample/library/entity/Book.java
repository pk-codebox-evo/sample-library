/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.List;

@NamePattern("%s|name")
@Table(name = "LIBRARY_BOOK")
@Entity(name = "library$Book")
public class Book extends StandardEntity {
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LITERATURE_TYPE_ID")
    private LiteratureType literatureType;

    @JoinTable(name = "LIBRARY_BOOK_AUTHOR_LINK",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    @ManyToMany
    private List<Author> authors;

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLiteratureType(LiteratureType literatureType) {
        this.literatureType = literatureType;
    }

    public LiteratureType getLiteratureType() {
        return literatureType;
    }


}