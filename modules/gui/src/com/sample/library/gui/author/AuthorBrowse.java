/*
 * Copyright (c) 2013 Haulmont
 */

package com.sample.library.gui.author;

import java.util.Map;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;

import javax.inject.Named;

public class AuthorBrowse extends AbstractLookup {

    @Named("authorTable.create")
    private CreateAction createAction;

    @Named("authorTable.edit")
    private EditAction editAction;

    @Override
    public void init(Map<String, Object> params) {
        createAction.setOpenType(WindowManager.OpenType.DIALOG);
        editAction.setOpenType(WindowManager.OpenType.DIALOG);
    }
}