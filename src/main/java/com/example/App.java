package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.Properties;



import java.sql.*;


import java.io.FileInputStream;
import java.io.IOException;


import java.util.Vector;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("unused")


public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/tableview2.fxml")
        );

        Scene scene = new Scene(loader.load(), 500, 500);
        stage.setTitle("Database Table Viewer");
            stage.setResizable(true); 

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



