/*
 * Copyright (c) 2013 Haulmont
 */

package com.sample.library.gui.book;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.sample.library.entity.Author;
import com.sample.library.entity.Book;
import com.sample.library.entity.LiteratureType;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class BookEdit extends AbstractEditor<Book> {

    @Named("authorsDs")
    private CollectionDatasource<Author, UUID> authorsDs;

    @Inject
    protected FieldGroup fieldGroup;

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    private CollectionDatasource<LiteratureType, UUID> literatureTypesDs;

    @Override
    public void init(Map<String, Object> params) {
        fieldGroup.addCustomField("literatureType", new FieldGroup.CustomFieldGenerator() {
            @Override
            public Component generateField(Datasource datasource, String propertyId) {
                LookupPickerField lookupPickerField = componentsFactory.createComponent(LookupPickerField.NAME);
                lookupPickerField.setOptionsDatasource(literatureTypesDs);
                lookupPickerField.setDatasource(datasource, propertyId);
                lookupPickerField.addLookupAction();
                return lookupPickerField;
            }
        });
    }

    public void addAuthor(Component source) {
        openLookup("library$Author.lookup", new Lookup.Handler() {
            @Override
            public void handleLookup(Collection items) {
                for (Object item : items) {
                    Author author = (Author) item;
                    authorsDs.addItem(author);
                }
            }
        }, WindowManager.OpenType.THIS_TAB);
    }
}