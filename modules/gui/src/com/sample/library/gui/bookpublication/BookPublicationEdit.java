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