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

package com.sample.library.desktop;

import javax.swing.*;

public class App extends com.haulmont.cuba.desktop.App {

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            app = new App();
            app.init(args);
            app.show();
            app.showLoginDialog();
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
    protected String getDefaultLogConfig() {
        return "desktop-logback.xml";
    }
}
