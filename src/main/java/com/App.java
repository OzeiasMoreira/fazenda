package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;


import java.io.IOException;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stageprimary) throws IOException {
        stage = stageprimary;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stageprimary.setTitle("Fazenda");
        stageprimary.setScene(scene);
        stageprimary.show();
    }

    public static void trocarTela(String fxml) throws IOException {
        Parent novaRoot = FXMLLoader.load(App.class.getResource(fxml));
        stage.getScene().setRoot(novaRoot);
    }

    public static void main(String[] args) {
        launch();
    }
}

