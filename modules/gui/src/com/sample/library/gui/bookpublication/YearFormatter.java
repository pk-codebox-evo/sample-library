/*
 * Copyright (c) 2014 Haulmont
 */

package com.sample.library.gui.bookpublication;

import com.haulmont.cuba.gui.components.Formatter;

/**
 * This formatter is needed because the default format for {@link com.haulmont.chile.core.datatypes.impl.IntegerDatatype}
 * contains grouping separator. We could redefine the whole <code>datatypes.xml</code> for the project, but we
 * chose to create this custom formatter and use it in UI components explicitly.
 */
public class YearFormatter implements Formatter<Integer> {

    @Override
    public String format(Integer value) {
        return value == null ? "" : String.valueOf(value);
    }
}
