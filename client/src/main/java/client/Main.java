/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package client;

import static com.google.inject.Guice.createInjector;

import client.scenes.MainCtrl;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the entry point for the client.
 */
public class Main extends Application {

    private static final Injector INJECTOR = createInjector(new MyModule());
    public static final ConfigManager configManager = new ConfigManager("config.properties");

    public static final MyFXML FXML = new MyFXML(INJECTOR);


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        var mainCtrl = INJECTOR.getInstance(MainCtrl.class);
        mainCtrl.initialize(primaryStage, FXML);
    }
}