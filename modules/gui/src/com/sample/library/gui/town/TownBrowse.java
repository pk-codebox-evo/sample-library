/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.gui.town;

import java.util.Map;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;

import javax.inject.Named;

public class TownBrowse extends AbstractLookup {

    @Named("townTable.create")
    private CreateAction createAction;

    @Named("townTable.edit")
    private EditAction editAction;

    @Override
    public void init(Map<String, Object> params) {
        createAction.setOpenType(WindowManager.OpenType.DIALOG);
        editAction.setOpenType(WindowManager.OpenType.DIALOG);
    }
}