package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Practice2 {
    public static void main(String[] args) {

        // 2번문제. "selectAllCategoryList" 쿼리 실행해보셈 -- 리스트로 출력되야 한다.

        /*1. 연결 객체 선언
                * 2. 컴파일시 담을 객체 선언 (PreparesStatement)
                * 3. 쿼리 결과를 담을 수 있는 객체 선언 (ResultSet)
                * 4. 쓸 쿼리 파일 뭐시기 선언 (properties)*/

        Connection con =getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Properties prop = new Properties();

        List<Map<Integer, String>> list = new ArrayList<>();
        String result = null;

        try {
            prop.loadFromXML( new FileInputStream("src/main/resources/mapper/manu-query.xml"));
            String query = prop.getProperty("selectAllCategoryList");// -> 쿼리에 넣어주는거 먼저 해줘야함



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
