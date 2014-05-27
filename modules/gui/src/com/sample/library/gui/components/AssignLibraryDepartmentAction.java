/*
 * Copyright (c) 2014 Haulmont
 */

package com.sample.library.gui.components;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.sample.library.entity.BookInstance;
import com.sample.library.gui.department_assigning.DepartmentAssigning;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Action that allows to assign library department to book instances, selected in a table.
 * <p/>
 * This action is used by BookInstanceBrowse and AccessionRegisterWindow.
 */
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
            // Parameters passed to DepartmentAssigning window
            Map<String, Object> params = new HashMap<>();
            params.put(DepartmentAssigning.INSTANCES_PARAM, bookInstances);
            params.put(DepartmentAssigning.VIEW_PARAM, booksInstancesTable.getDatasource().getView());

            final DepartmentAssigning departmentAssigningWindow = frame.openWindow("department-assigning",
                    WindowManager.OpenType.DIALOG, params);
            departmentAssigningWindow.addListener(new Window.CloseListener() {
                @Override
                public void windowClosed(String actionId) {
                    if (DepartmentAssigning.SUCCESS_ACTION.equals(actionId)) {
                        for (BookInstance assignedInstance : departmentAssigningWindow.getAssignedInstances()) {
                            // Put returned instances back into datasource
                            booksInstancesTable.getDatasource().updateItem(assignedInstance);
                        }
                    }
                }
            });
        } else {
            frame.showNotification(messages.getMainMessage("selectBookInstancesMessage.text"),
                    IFrame.NotificationType.HUMANIZED);
        }
    }
}
