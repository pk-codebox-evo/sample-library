/*
 * Copyright (c) 2013 Haulmont
 */

package com.sample.library.gui.bookpublication;

import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.sample.library.entity.Book;
import com.sample.library.entity.BookPublication;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class BookPublicationEdit extends AbstractEditor<BookPublication> {
    
    @Inject
    protected FieldGroup fieldGroup;

    @Inject
    protected ComponentsFactory componentsFactory;

    @Inject
    protected CollectionDatasource<Book, UUID> booksDs;

    @Override
    public void init(Map<String, Object> params) {
        fieldGroup.addCustomField("book", new FieldGroup.CustomFieldGenerator() {
            @Override
            public Component generateField(Datasource datasource, String propertyId) {
                LookupPickerField lookupPickerField = componentsFactory.createComponent(LookupPickerField.NAME);
                lookupPickerField.setOptionsDatasource(booksDs);
                lookupPickerField.setDatasource(datasource, propertyId);
                lookupPickerField.addLookupAction();
                lookupPickerField.addOpenAction();
                return lookupPickerField;
            }
        });
    }

    @Override
    protected void postInit() {
        if (!PersistenceHelper.isNew(getItem()) || getItem().getBook() != null) {
            fieldGroup.setEditable("book", false);
        }
    }
}