package com.ohgiraffers.section01;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.getConnection2;

public class Singleton {

    public static void main(String[] args) {

        // 싱글톤 객체 확인

        Connection con = getConnection();
        Connection con2 = getConnection(); // 위에는 싱글톤이라 돌려쓰는거고

        System.out.println( con );
        System.out.println( con2 );

        System.out.println( " ---------------------- ");
        Connection con3 = getConnection2();
        Connection con4 = getConnection2();

        System.out.println( con3 ); // 얘(getConnection) 는 싱글톤 아니라 매번 새로 생성해주기 때문에 다른 값이 출력이 되는거임
        System.out.println( con4 );

    }
}
