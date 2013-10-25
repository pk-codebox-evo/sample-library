/*
 * Copyright (c) 2013 Haulmont
 */

package com.sample.library.gui.department_assigning;

import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.LookupField;
import com.sample.library.service.BookInstanceService;
import com.sample.library.entity.BookInstance;
import com.sample.library.entity.LibraryDepartment;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Map;

public class DepartmentAssigning extends AbstractWindow {

    @Inject
    private BookInstanceService bookInstanceService;

    @Named("libraryDepartmentField")
    private LookupField libraryDepartmentField;

    @WindowParam(name = "booksInstances")
    private Collection<BookInstance> booksInstances;

    @Override
    public void init(Map<String, Object> params) {
        getDialogParams().setWidth(400);
    }

    public void assign(Component source) {
        LibraryDepartment libraryDepartment = libraryDepartmentField.getValue();
        if (libraryDepartment != null) {
            bookInstanceService.assignLibraryDepartment(booksInstances, libraryDepartment);
            close("");
        } else {
            showNotification(getMessage("selectLibraryDepartmentMessage.text"), NotificationType.HUMANIZED);
        }
    }

    public void close(Component source) {
        close("");
    }
}