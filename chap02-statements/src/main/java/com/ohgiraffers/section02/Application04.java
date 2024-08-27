package com.ohgiraffers.section02;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application04 {
    public static void main(String[] args) {

        // xml 파일을 이용한 조회
        // xml - 특수한 목적을 가진 마크업 언어

        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Scanner scr = new Scanner(System.in);
        System.out.println(" 성씨를 입력 해주세요 : ");
        String empName = scr.nextLine();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));
            ps = con.prepareStatement(prop.getProperty("selectEmpByName"));
            ps.setString(1, empName);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            close(con);
            close(rs);
            close(ps);
        }
    }
}








