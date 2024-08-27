package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null; // statement 를 상속받은형태임
        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println( rset.getString(1) + " " + rset.getString(2));
            } // 인덱스 번호로 넣어도 되고 그냥 명칭으로 넣어도 가능
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }

    }
}
