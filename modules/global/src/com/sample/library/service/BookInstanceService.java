/*
 * Copyright (c) 2013 Haulmont
 */

package com.sample.library.service;

import com.sample.library.entity.BookInstance;
import com.sample.library.entity.BookPublication;
import com.sample.library.entity.LibraryDepartment;

import java.lang.String;
import java.util.Collection;

public interface BookInstanceService {

    String NAME = "library_BookInstanceService";

    Collection<BookInstance> createBookInstances(BookPublication bookPublication, Integer amount);

    void assignLibraryDepartment(Collection<BookInstance> booksInstances, LibraryDepartment libraryDepartment);
}