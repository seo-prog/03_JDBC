package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application02 {
    public static void main(String[] args) {


        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        /*
        * PreparesStatement
        * - 특이하게 같은 쿼리를 보내면 기억해둔다.( 저장해둔다.)
        * - 완성된 쿼리문과 미완성된 쿼리문을 모두 사용할 수 있다.
        * - 미완성 쿼리 = 위치홀더 (?) 를 사용한 쿼리문
        * // 그냥 statement 는 미완성된 쿼리문을 못쓴다.
        * */

        try {
            ps = con.prepareStatement(" SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?"); // 이걸 미완성 쿼리라고 한다.
            // 첫 번째 물음표가 1, 두 번째 물음표가 2 ... 일케 순서대로 ? 의 순서번호을 인덱스 번호로 생각하고
            // 밑에 인덱스 번호와 값을 적어두면 된다.

            ps.setString(1, "200");

            rs = ps.executeQuery(); // 이게 실행되는 시점에 합쳐진다고 생각
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
