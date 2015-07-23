/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.gui.bookpublication;

import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.sample.library.entity.BookPublication;

import javax.inject.Inject;
import java.util.Map;

public class BookPublicationEdit extends AbstractEditor<BookPublication> {
    
    @Inject
    protected FieldGroup fieldGroup;

    @Override
    public void init(Map<String, Object> params) {
        LookupPickerField bookField = (LookupPickerField) fieldGroup.getFieldComponent("book");
        bookField.addLookupAction();
        bookField.addOpenAction();
    }

    @Override
    protected void postInit() {
        if (!PersistenceHelper.isNew(getItem()) || getItem().getBook() != null) {
            fieldGroup.setEditable("book", false);
        }
    }
}