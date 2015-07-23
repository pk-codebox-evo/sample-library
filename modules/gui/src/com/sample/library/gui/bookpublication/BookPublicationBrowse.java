/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.gui.bookpublication;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.sample.library.entity.BookPublication;

import javax.inject.Inject;
import java.util.Collections;

public class BookPublicationBrowse extends AbstractLookup {

    @Inject
    Table bookPublicationTable;

    public void browseInstances(Component source) {
        BookPublication bookPublication = bookPublicationTable.getSingleSelected();
        if (bookPublication != null) {
            openWindow("library$BookInstance.lookup", WindowManager.OpenType.THIS_TAB,
                    Collections.<String, Object>singletonMap("bookPublication", bookPublication));
        } else {
            showNotification(getMessage("selectBookPublicationMessage.text"), NotificationType.HUMANIZED);
        }
    }
}