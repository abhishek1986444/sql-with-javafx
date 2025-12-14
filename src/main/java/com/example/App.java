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



@SuppressWarnings("unused")




public class App  {

public static void main (String [] args ) throws Exception
{
DBConnection object = new DBConnection();



struct admin = object.User_Connection();

Statement stmt = admin.connection.createStatement();

ResultSet rs = stmt.executeQuery("Select * from " + admin.master_Table + "  ;  ");

ResultSetMetaData rsmd =  rs.getMetaData();


System.out.println(rsmd.getColumnCount());



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