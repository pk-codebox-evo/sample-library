/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.gui.bookinstance;

import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Table;
import com.sample.library.entity.BookPublication;
import com.sample.library.gui.components.AssignLibraryDepartmentAction;

import javax.inject.Inject;
import java.util.Map;

public class BookInstanceBrowse extends AbstractLookup {

    @Inject
    private Table bookInstanceTable;

    @Inject
    private Label bookTitleLabel;

    @WindowParam(name = "bookPublication", required = true)
    private BookPublication publication;

    @Override
    public void init(Map<String, Object> params) {
        addAction(new AssignLibraryDepartmentAction(bookInstanceTable));

        if (publication != null) {
            bookTitleLabel.setValue(String.format(getMessage("book"),
                    publication.getBook().getName(), publication.getPublisher().getName(), publication.getYear()));
        }
    }
}