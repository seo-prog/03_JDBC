package com.ohgiraffers.understand;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {


        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/understand/employee-query.xml"));
            ps = con.prepareStatement(prop.getProperty("SUN"));
            ps.setString(1, "선");
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1) + ", " +
                        rs.getString(2) + ", " + rs.getString(3));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(ps);
            close(rs);

        }

    }
}
