package com.ohgiraffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {


        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/resources/connection-info.properties"));
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            con = DriverManager.getConnection(url, user, password);
            // 우리의 데이터베이스가 con 객체에 담긴다는 뜻

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement st) {
        try {
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
