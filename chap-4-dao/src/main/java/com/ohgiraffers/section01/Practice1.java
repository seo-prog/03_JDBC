package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Practice1 {
    public static void main(String[] args) {

        /*
        * 1. 연결 객체 선언
        * 2. 컴파일시 담을 객체 선언 (PreparesStatement)
        * 3. 쿼리 결과를 담을 수 있는 객체 선언 (ResultSet)
        * 4. 쓸 쿼리 파일 뭐시기 선언 (properties)
        *
        * 5. 파일 경로 써주는 그 문장
        * --- ---1. 경로 쓸 쿼리 선언해준 객체 생각(load, FileInputStream)
        * 6. 쿼리라는 객체를 만들고 그 안에 넣어준다. 어떤 쿼리를 쓸껀지 ! (getProperty)
        * 7. 쿼리문을 담아준다. 우리가 만든 객체에 (prepareStatement)
        * 8. 쿼리의 결과를 담아주는 특수 객체에 결과를 담아준다.(executeQuery) -> 쿼리 결과를 담아주는
        * 9. 이제 이 결과를 우리가 볼 수 있게끔 형변환(?) 해준다. ---> 여가서 중요! 결과값을 담아줄 객체를 만들고 그걸 우리가 볼 수 있게 형변환 해주는 result 객체를 또 따로 만드는것이다 !
        * (rs.getString()) --> () 안에는 원하는 컬럼명을 작성
        *
        *
        *
        * */

        // 1번문제 . "selectLastMenuCode" 실행하는 문제

        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Properties pro = new Properties();

        String result = null;


        try {
            pro.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = pro.getProperty("selectLastMenuCode");
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("MAX(MENU_CODE)");
            }
            System.out.println( " 가장 최근 입력된 코드는 ~! " + result );



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            close(con);
            close(rs);
            close(ps);
        }


    }
}
