package com.isil.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainApp {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver"); // Para instanciar el DRIVER - PPT

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TRU", //Recordar poner el nombre de la BD
                "root",
                "" //El password debe ser el mismo de mi usuario de XAMP
        );

        Statement stmt= con.createStatement();

        int resultUpdate = stmt.executeUpdate("update Users set phone='44445666' where name='Messi' ");
        //Con este metodo se ejecuta los Updates

        ResultSet result = stmt.executeQuery("SELECT * FROM Users");
        //"executeQuery" sirve para poner Data

        while(result.next()){ // buscar con "if"
            //if(result.getString("name").equals("Ronaldo")){System.out...}
            System.out.println(result.getString("idUser"));
            System.out.println(result.getString("name"));
            System.out.println(result.getString("phone"));
            System.out.println(result.getString("city"));
        }
    }
}
