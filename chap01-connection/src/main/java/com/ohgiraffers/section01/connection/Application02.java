package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/employee";
        String user = "gangnam";
        String password = "gangnam";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url,user,password);

            System.out.println( conn );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
           try{
               conn.close();
           }catch (SQLException e){
               throw new RuntimeException(e);
           }
        }


    }
}
