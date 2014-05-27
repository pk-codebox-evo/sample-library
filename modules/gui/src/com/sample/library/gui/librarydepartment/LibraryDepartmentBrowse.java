/*
 * Copyright (c) 2014 Haulmont
 */

package com.sample.library.gui.librarydepartment;

import java.util.Map;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;

import javax.inject.Named;

public class LibraryDepartmentBrowse extends AbstractLookup {

    @Named("libraryDepartmentTable.create")
    private CreateAction createAction;

    @Named("libraryDepartmentTable.edit")
    private EditAction editAction;

    @Override
    public void init(Map<String, Object> params) {
        createAction.setOpenType(WindowManager.OpenType.DIALOG);
        editAction.setOpenType(WindowManager.OpenType.DIALOG);
    }
}