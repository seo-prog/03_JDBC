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
        //  load : 테이블의 내용을 불어오기 하는 명령

        // FileReader 객체는 웹 애플리케이션이 비동기적으로
        // 데이터를 읽기 위하여 읽을 파일을 가리키는 File 을 이용해 파일의 내용을 읽고
        // 사용자의 컴퓨터에 저장하는 것을 가능하게 해줍니다.


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
