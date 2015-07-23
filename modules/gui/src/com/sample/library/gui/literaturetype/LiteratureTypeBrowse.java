/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.gui.literaturetype;

import java.util.Map;

import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;

import javax.inject.Inject;
import javax.inject.Named;
import com.haulmont.cuba.gui.components.Component;

public class LiteratureTypeBrowse extends AbstractLookup {

    @Named("literatureTypeTable.create")
    private CreateAction createAction;

    @Named("literatureTypeTable.edit")
    private EditAction editAction;
    
    @Inject
    private CollectionDatasource literatureTypeDs;

    @Override
    public void init(Map<String, Object> params) {
        createAction.setOpenType(WindowManager.OpenType.DIALOG);
        editAction.setOpenType(WindowManager.OpenType.DIALOG);
    }

    public void refreshTable(Component source) {
        literatureTypeDs.refresh();
    }
}