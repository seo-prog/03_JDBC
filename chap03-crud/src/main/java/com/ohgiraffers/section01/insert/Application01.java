package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement ps = null;
        int result = 0; // 결과를 담아줄 변수  성공여부만 담는다.select 와 다른점 !

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            ps = con.prepareStatement(prop.getProperty("insertMenu"));
            ps.setString(1,"쌀국수");
            ps.setInt(2, 11900);
            ps.setInt(3, 4);
            ps.setString(4, "Y" );

            result = ps.executeUpdate();

            System.out.println(" 결과 : *^^* " + result );

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(ps);

        }


    }
}
