/*
 * Copyright (c) 2013 Haulmont
 */

package com.sample.library.desktop;

import javax.swing.*;

public class App extends com.haulmont.cuba.desktop.App {

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                app = new App();
                app.init(args);
                app.show();
                app.showLoginDialog();
            }
        });

    }

    @Override
    protected String getDefaultAppPropertiesConfig() {
        return "/cuba-desktop-app.properties /desktop-app.properties";
    }

    @Override
    protected String getDefaultHomeDir() {
        return System.getProperty("user.home") + "/.cuba/library";
    }

    @Override
    protected String getDefaultLog4jConfig() {
        return "desktop-log4j.xml";
    }
}
