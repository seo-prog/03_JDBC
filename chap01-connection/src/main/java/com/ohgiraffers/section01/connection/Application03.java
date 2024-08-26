package com.ohgiraffers.section01.connection;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application03 {
    public static void main(String[] args) {

        // properties 파일에 있는 정보를 가져올 객체

        Properties prop = new Properties();
        Connection con = null;
        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            try {
                con = DriverManager.getConnection(url,user,password);
                System.out.println( con );

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);

        }finally {
            try {

                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
