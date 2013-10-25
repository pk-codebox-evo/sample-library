/*
 * Copyright (c) 2013 Haulmont
 */

package com.sample.library.gui.components;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.sample.library.entity.BookInstance;

import java.util.Collections;
import java.util.Set;

public class AssignLibraryDepartmentAction extends AbstractAction {

    private Table booksInstancesTable;

    public AssignLibraryDepartmentAction(Table booksInstancesTable) {
        super("assignLibraryDepartment");
        this.booksInstancesTable = booksInstancesTable;
    }

    @Override
    public void actionPerform(Component component) {
        IFrame frame = booksInstancesTable.getFrame();

        Set<BookInstance> bookInstances = booksInstancesTable.getSelected();
        if (!bookInstances.isEmpty()) {
            Window libraryDepartmentAssigningWindow = frame.openWindow("department-assigning",
                    WindowManager.OpenType.DIALOG,
                    Collections.<String, Object>singletonMap("booksInstances", bookInstances));
            libraryDepartmentAssigningWindow.addListener(new Window.CloseListener() {
                @Override
                public void windowClosed(String actionId) {
                    booksInstancesTable.getDatasource().refresh();
                }
            });
        } else {
            frame.showNotification(messages.getMainMessage("selectBookInstancesMessage.text"),
                    IFrame.NotificationType.HUMANIZED);
        }
    }
}
