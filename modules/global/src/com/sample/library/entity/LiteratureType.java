/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "LIBRARY_LITERATURE_TYPE")
@Entity(name = "library$LiteratureType")
public class LiteratureType extends StandardEntity {
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}