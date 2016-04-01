/*
 * Copyright (c) 2016 Haulmont
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.library.gui.book;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.sample.library.entity.Book;
import com.sample.library.entity.LiteratureType;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class BookEdit extends AbstractEditor<Book> {
    @Inject
    protected FieldGroup fieldGroup;

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    private CollectionDatasource<LiteratureType, UUID> literatureTypesDs;

    @Override
    public void init(Map<String, Object> params) {
        fieldGroup.addCustomField("literatureType", (datasource, propertyId) -> {
            LookupPickerField lookupPickerField = componentsFactory.createComponent(LookupPickerField.class);
            lookupPickerField.setOptionsDatasource(literatureTypesDs);
            lookupPickerField.setDatasource(datasource, propertyId);
            lookupPickerField.addLookupAction();
            return lookupPickerField;
        });
    }
}