package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application{

    public static void main(String[] args) throws IOException
    {
        launch(args);


    }

    public void start (Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../../sample.fxml"));
        VBox stackpane = loader.load();

        Scene Menu = new Scene(stackpane);

        primaryStage.setScene(Menu);
        primaryStage.setTitle("Klient");
        primaryStage.show();

    }
}
