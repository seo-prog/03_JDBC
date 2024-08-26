package com.ohgiraffers.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {

        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            try {
                con = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return con;

    }

    public static void close(Connection con) {
        try {
            con.close();// 우리가 의도한 작업을 끝낸 이후에 close 를 함으로써 DB 와의 연결을 끊어주는거임
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
