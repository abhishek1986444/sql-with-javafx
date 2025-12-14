package com.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



import java.sql.*;


import java.io.FileInputStream;
import java.util.Properties;



public class TableController {

    @FXML
    private TableView<ObservableList<String>> tableView;

    @FXML
    public void initialize() {
        loadTableFromDatabase();
    }

    private void loadTableFromDatabase() {

        try {
            DBConnection object = new DBConnection();
            struct admin = object.User_Connection();

            Statement stmt = admin.connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM " + admin.master_Table + ";"
            );

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // ---- Create columns dynamically ----
            for (int i = 1; i <= columnCount; i++) {
                final int colIndex = i - 1;

                TableColumn<ObservableList<String>, String> column =
                        new TableColumn<>(rsmd.getColumnName(i));

                column.setCellValueFactory(data ->
                        new SimpleStringProperty(data.getValue().get(colIndex))
                );

                tableView.getColumns().add(column);
            }

            // ---- Load rows ----
            ObservableList<ObservableList<String>> data =
                    FXCollections.observableArrayList();

            while (rs.next()) {
                ObservableList<String> row =
                        FXCollections.observableArrayList();

                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            tableView.setItems(data);

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




class struct {

Connection connection;

String master_Table;





}




class DBConnection{
    public struct User_Connection() throws Exception
    {
        struct object = new struct();

       
            Properties props = new Properties();


            FileInputStream  fis = new FileInputStream(
    "C:/Users/ABHISHEK/Downloads/deadnote/version_2/withjavafx/db.properties"
            );
        
         props.load(fis);


       String url = props.getProperty("db.url");

      String driver = props.getProperty("db.driver");

     String password = props.getProperty("db.password");
        String user = props.getProperty("db.user");



                     
                     
 object.master_Table = props.getProperty("db.master_db_table");

 Class.forName(driver);

 object.connection = DriverManager.getConnection(url,user,password);


 System.out.println("Connection successful!");


        return object;


    }
}