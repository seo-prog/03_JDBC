package com.ohgiraffers.section02;

import java.sql.*;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {
        // CAST("2024.08.27" AS DATE)
        //CAST(PRICE AS CHAR(5)) -- CAST 는 형변환을 해주는 함수이다. 그냥 CONCAT 에 써도 자동 형변환이고 수동으로 해주려면 CAST 를 써야함

        // 성씨를 입력 받아 해당 성을 가진 사원 조회
        // SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME CONCAT(?,'%')

        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null; // 실행시키는 객체
        Scanner sc = new Scanner(System.in);


        System.out.println("입력 받을 성씨를 입력 해주세요 : *^^* ");
        String input = sc.nextLine();
        String Q = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME like CONCAT(?,'%')";// 문자열이면 무조건 '' 이거 붙여줘야함 !!

        try {
            ps = con.prepareStatement(Q); // 쿼리문을 날릴 객체
            ps.setString(1, input); // 위 ps 로 들어간다고 생각~! ? 를 채워주는거다
            System.out.println( ps ); // 잘 들어갔는지 고냥 확인하려는 거임
            rs = ps.executeQuery(); // 여기까지 해야 완성형 쿼리가 되는거 ! 쿼리완성 시점 !
            // 위 ps 쿼리를 .executeQuery() 로 완성시킴


            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(rs);
            close(ps);

        }

    }
}
