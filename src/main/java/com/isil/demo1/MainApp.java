package com.isil.demo1;

import java.sql.*;

public class MainApp {
    public static void main(String[] args) throws Exception{

        //1. Cargar driver
        Class.forName("com.mysql.cj.jdbc.Driver"); // Para instanciar el DRIVER - PPT

        //2. Crear conexion
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TRU", //Recordar poner el nombre de la BD
                "root",
                "" //El password debe ser el mismo de mi usuario de XAMP
        );

        //3. Crear statement
        Statement stmt= con.createStatement();

        //4. Ejecutar query
        int resultUpdate = stmt.executeUpdate("update Users set phone='44445666' where name='Messi' ");
        //Con este metodo se ejecuta los Updates
        ResultSet result = stmt.executeQuery("SELECT * FROM Users");
        //"executeQuery" sirve para poner Data

        //5. Reconocer resultados
        while(result.next()){ // buscar con "if"
            // s-if(result.getString("name").equals("Ronaldo")){System.out...}
            System.out.println(result.getString("idUser"));
            System.out.println(result.getString("name"));
            System.out.println(result.getString("phone"));
            System.out.println(result.getString("city") + "\n");
        }


        Statement stmt2= con.createStatement();
        ResultSet resultSet = stmt2.executeQuery("select * from Users where name='Ronaldo'");
        while(resultSet.next()){
            System.out.println(resultSet.getString("name") + "\n" +
            resultSet.getString("phone") + "\n" +
            resultSet.getString("city") + "\n");
        }

        //Otra forma de crear Statement cuando no quiera poner en duro el valor que se esta buscando
        PreparedStatement preparedStatement =
                con.prepareStatement("select * from Users where name=? and city=?");
        preparedStatement.setString(1,"Messi"); //Si los valores estan mal
        preparedStatement.setString(2,"Rosario"); //ingresados, no botara nada
        ResultSet resultSet2 = preparedStatement.executeQuery();

        while(resultSet2.next()){
            System.out.println(resultSet2.getString("name") + "\n" +
                    resultSet2.getString("phone") + "\n" +
                    resultSet2.getString("city"));
        }

        //3.1 Statement mantenimiento
        Statement stCreate = con.createStatement();
        int filasAfectadas =
                stCreate.executeUpdate("INSERT INTO Users VALUES (6,'Maria','98798721','Quito')");
        System.out.println("Filas afectadas: " + filasAfectadas + "\n");

        //3.2 Statement Consulta
        PreparedStatement preparedStatement1 =
                con.prepareStatement("select * from Users where name = ?");
        preparedStatement1.setString(1,"Maria");


        //3.3 UPDATE
        //Statement stCreate = con.createStatement();
        //        int filasAfectadas1 =
        //                stCreate.executeUpdate("UPDATE USERS SET NAME='JUAN' WHERE IDUSER=1");
        //        System.out.println("Filas afectadas: " + filasAfectadas + "\n");
        //PreparedStatement preparedStatement1 =
        //                con.prepareStatement("select * from Users where IDUSER = ?");
        //        preparedStatement1.setString(1,"1");


        //4.1 Ejecutar Query
        ResultSet resultSet1 = preparedStatement1.executeQuery();

        //5.1 Recorrer resultados
        while(resultSet1.next()){
            System.out.println(resultSet1.getString("name") + "\n" +
                    resultSet1.getString("phone") + "\n" +
                    resultSet1.getString("city"));
        }

        //6. Cerrar conexion
        con.close();
    }
}
