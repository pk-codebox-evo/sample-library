/*
 * Copyright (c) 2013 Haulmont
 */

package com.sample.library.gui.accession;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;

import java.util.*;

import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.ValueListener;
import com.sample.library.service.BookInstanceService;
import com.sample.library.entity.Book;
import com.sample.library.entity.BookInstance;
import com.sample.library.entity.BookPublication;
import com.sample.library.gui.components.AssignLibraryDepartmentAction;

import javax.inject.Inject;
import javax.inject.Named;

public class AccessionRegisterWindow extends AbstractWindow {

    @Inject
    private BookInstanceService bookInstanceService;

    @Named("booksDs")
    private CollectionDatasource<Book, UUID> booksDs;

    @Named("bookPublicationsDs")
    private CollectionDatasource<BookPublication, UUID> bookPublicationsDs;

    @Named("bookInstancesDs")
    private CollectionDatasource<BookInstance, UUID> bookInstancesDs;

    @Named("bookField")
    private LookupPickerField bookField;

    @Named("bookPublicationsTable")
    private Table bookPublicationsTable;

    @Named("bookInstancesAmountField")
    private TextField bookInstancesAmountField;

    @Named("bookInstancesTable")
    private Table bookInstancesTable;

    @Override
    public void init(Map<String, Object> params) {
        bookField.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                bookPublicationsDs.refresh();
            }
        });

        addAction(new AssignLibraryDepartmentAction(bookInstancesTable));

        bookInstancesDs.refresh(Collections.<String, Object>singletonMap(
                "bookInstancesIds", Collections.singletonList(UUID.randomUUID())));
    }

    public void createBook(Component source) {
        final Window.Editor bookEditor = openEditor(
                "library$Book.edit", new Book(), WindowManager.OpenType.THIS_TAB
        );
        bookEditor.addListener(new CloseListener() {
            @Override
            public void windowClosed(String actionId) {
                booksDs.refresh();
                bookField.setValue(bookEditor.getItem());
            }
        });
    }

    public void createBookPublication(Component source) {
        Book book = bookField.getValue();
        if (book == null) {
            showNotification(getMessage("selectBookMessage.text"), NotificationType.HUMANIZED);
            return;
        }

        BookPublication bookPublication = new BookPublication();
        bookPublication.setBook(book);
        Window.Editor bookPublicationEditor = openEditor(
                "library$BookPublication.edit", bookPublication, WindowManager.OpenType.THIS_TAB
        );
        bookPublicationEditor.addListener(new CloseListener() {
            @Override
            public void windowClosed(String actionId) {
                bookPublicationsDs.refresh();
            }
        });
    }

    public void createBookInstances(Component source) {
        BookPublication bookPublication = bookPublicationsTable.getSingleSelected();
        if (bookPublication == null) {
            showNotification(getMessage("selectBookPublicationMessage.text"), NotificationType.HUMANIZED);
            return;
        }

        Integer bookInstancesAmount = bookInstancesAmountField.getValue();

        if (bookInstancesAmount == null || bookInstancesAmount == 0) {
            showNotification(getMessage("setBookInstancesAmountMessage.text"), NotificationType.HUMANIZED);
            return;
        }

        if (bookInstancesAmount > 100) {
            showNotification(getMessage("bigBookInstancesAmountMessage.text"), NotificationType.HUMANIZED);
            return;
        }

        Collection<BookInstance> bookInstances = bookInstanceService.createBookInstances(
                bookPublication, bookInstancesAmount);

        Collection<UUID> bookInstancesIds = new ArrayList<>();
        for (BookInstance bookInstance : bookInstances) {
            bookInstancesIds.add(bookInstance.getId());
        }
        bookInstancesDs.refresh(Collections.<String, Object>singletonMap("bookInstancesIds", bookInstancesIds));
    }
}