/*
 * Copyright (c) 2015 Haulmont
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