package com.ohgiraffers.section03.sqlinjection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {

   private static String empId = "210";

   private static String empName = "' OR 1=1 AND EMP_ID = '203";
   // 숫자가 뭐든 이름을 몰라도 정보를 빼내올 수 있다.
    // statement 는 뒤에 붙는걸 쿼리로 읽어서 실행이 되고 PreparedStatement 는 뒤에 붙는 모든걸 문자열로 읽기 때문에 공격을 막을 수 있다.

    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "' AND EMP_NAME = '" + empName + "'";
        System.out.println( query );

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
           if (rset.next()) {
               System.out.println( rset.getString("EMP_NAME") + " 님 환영합니다 *^^* ");
           }else{
               System.out.println( " 회원 정보가 존재하지 않습니다 ㅠㅠ;;");
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);

        }

    }

}
