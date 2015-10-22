/*
 * Copyright (c) 2015 Haulmont
 */

package com.sample.library.gui.book;

import java.util.Map;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.reports.gui.actions.RunReportAction;

import javax.inject.Inject;

public class BookBrowse extends AbstractLookup {

    @Inject
    private Button reportButton;

    @Override
    public void init(Map<String, Object> params) {
        reportButton.setAction(new RunReportAction("report", this));
    }
}