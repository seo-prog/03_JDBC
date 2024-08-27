package com.ohgiraffers.understand;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static java.sql.JDBCType.CHAR;

public class Application02 {
    public static void main(String[] args) {


        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/understand/employee-query.xml"));
            ps = con.prepareStatement(prop.getProperty("selectEmpBy"));
            String a = "1";

            ps.setInt(1, 1 );
            System.out.println( ps );
            rs = ps.executeQuery();
            System.out.println( rs );

            while (rs.next()) {
                System.out.println(rs.getString(1) + ", " +
                        rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4)+", " + rs.getString(5)+", " + rs.getString(6)+", " + rs.getString(7)+", " + rs.getString(8)+", " + rs.getString(9)+", " + rs.getString(10)+", " + rs.getString(11)+", " + rs.getString(12));
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


