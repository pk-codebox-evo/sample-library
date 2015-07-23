/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.service;

import com.haulmont.cuba.core.global.View;
import com.sample.library.entity.BookInstance;
import com.sample.library.entity.BookPublication;
import com.sample.library.entity.LibraryDepartment;

import java.util.Collection;

public interface BookInstanceService {

    String NAME = "library_BookInstanceService";

    /**
     * Create and return new book instances.
     */
    Collection<BookInstance> createBookInstances(BookPublication bookPublication, Integer amount);

    /**
     * Set department attribute in book instances and return updated entities.
     */
    Collection<BookInstance> assignLibraryDepartment(Collection<BookInstance> bookInstances,
                                                     LibraryDepartment libraryDepartment, View bookInstanceView);
}