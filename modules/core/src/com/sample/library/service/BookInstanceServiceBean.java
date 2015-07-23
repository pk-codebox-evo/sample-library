/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.global.AccessDeniedException;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.entity.PermissionType;
import com.sample.library.entity.BookInstance;
import com.sample.library.entity.BookPublication;
import com.sample.library.entity.LibraryDepartment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

@Service(BookInstanceService.NAME)
public class BookInstanceServiceBean implements BookInstanceService {

    @Inject
    private Persistence persistence;

    @Inject
    private UniqueNumbersAPI uniqueNumbers;

    @Inject
    private Security security;

    @Override
    @Transactional
    public Collection<BookInstance> createBookInstances(BookPublication bookPublication, Integer amount) {
        checkPermission(EntityOp.CREATE);
        // Due to the @Transactional annotation a new transaction is started right before the method is called and
        // committed after leaving the method
        Collection<BookInstance> bookInstances = new ArrayList<>();
        for (int i = 0; i < amount; ++i) {
            BookInstance bookInstance = new BookInstance();
            bookInstance.setBookPublication(bookPublication);
            bookInstance.setInventoryNumber(uniqueNumbers.getNextNumber("inventory_number"));

            persistence.getEntityManager().persist(bookInstance);

            bookInstances.add(bookInstance);
        }
        return bookInstances;
    }

    @Override
    public Collection<BookInstance> assignLibraryDepartment(Collection<BookInstance> bookInstances,
                                                            LibraryDepartment libraryDepartment, View bookInstanceView) {
        checkPermission(EntityOp.UPDATE);
        Collection<BookInstance> mergedInstances = new ArrayList<>();
        // Explicit transaction control
        Transaction tx = persistence.createTransaction();
        try {
            EntityManager em = persistence.getEntityManager();
            for (BookInstance booksInstance : bookInstances) {
                BookInstance instance = em.merge(booksInstance);
                instance.setLibraryDepartment(libraryDepartment);
                // Fetch the object graph specified by the view
                em.fetch(instance, bookInstanceView);
                // Return the updated instance
                mergedInstances.add(instance);
            }
            tx.commit();
        } finally {
            tx.end();
        }
        return mergedInstances;
    }

     private void checkPermission(EntityOp op) {
        if (!security.isEntityOpPermitted(BookInstance.class, op)) {
            throw new AccessDeniedException(PermissionType.ENTITY_OP, BookInstance.class.getSimpleName());
        }
    }
}