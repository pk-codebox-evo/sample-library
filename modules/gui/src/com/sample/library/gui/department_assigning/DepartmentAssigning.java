/*
 * Copyright (c) 2013 Haulmont
 */

package com.sample.library.gui.department_assigning;

import com.haulmont.cuba.core.app.DataService;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.LookupField;
import com.sample.library.entity.BookInstance;
import com.sample.library.entity.LibraryDepartment;
import com.sample.library.service.BookInstanceService;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

public class DepartmentAssigning extends AbstractWindow {

    public static final String INSTANCES_PARAM = "bookInstances";
    public static final String VIEW_PARAM = "bookInstanceView";

    public static final String SUCCESS_ACTION = "success";

    @Inject
    private BookInstanceService bookInstanceService;

    @Inject
    private LookupField libraryDepartmentField;

    @WindowParam(name = INSTANCES_PARAM, required = true)
    private Collection<BookInstance> bookInstances;

    @WindowParam(name = VIEW_PARAM, required = true)
    private View bookInstanceView;

    private Collection<BookInstance> assignedInstances;

    @Override
    public void init(Map<String, Object> params) {
        getDialogParams().setWidth(400);
    }

    public void assign(Component source) {
        LibraryDepartment libraryDepartment = libraryDepartmentField.getValue();
        if (libraryDepartment != null) {
            assignedInstances = bookInstanceService.assignLibraryDepartment(
                    bookInstances, libraryDepartment, bookInstanceView);
            close(SUCCESS_ACTION);
        } else {
            showNotification(getMessage("selectLibraryDepartmentMessage.text"), NotificationType.HUMANIZED);
        }
    }

    public void close(Component source) {
        close("");
    }

    public Collection<BookInstance> getAssignedInstances() {
        return assignedInstances;
    }
}