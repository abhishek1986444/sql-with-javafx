
  
    

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

import java.util.logging.*;

@SuppressWarnings("unused")



public class tableviewcontroller1 {


    @FXML  private TableView<ObservableList<String>>tableview;


    public void initialize()
    {
        try {

            DBConnection db = new DBConnection();
            struct ctx = db.User_Connection();
            loadtable(ctx.connection, ctx.master_Table);

        }


        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void loadtable(Connection conn, String tableName ) throws Exception{

     Statement stmt = conn.createStatement();

     ResultSet rs = stmt.executeQuery("Select * from   " + tableName + "   ;  ");

     ResultSetMetaData meta = rs.getMetaData();

     int colCount = meta.getColumnCount();

     tableview.getColumns().clear();

     for ( int i = 1 ; i<= colCount ; i++)
     {
        final int index = i - 1 ;
        
        TableColumn<ObservableList<String> , String > col =

       new TableColumn<>(meta.getColumnName(i));

       col.setCellValueFactory(data -> 

 new SimpleStringProperty(data.getValue().get(index))

       );

       tableview.getColumns().add(col);

     }
 

      while ( rs.next())
      {
        ObservableList<String> row = FXCollections.observableArrayList();


        for ( int i = 1 ; i<=colCount ; i++)
        {
            row.add(rs.getString(i));
        }
        tableview.getItems().add(row);
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