package com.ohgiraffers.section03;

import java.sql.Connection;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        // transaction 처리
        Connection con = getConnection2();
        try {
            con.setAutoCommit(false); // connectin 객체의 autocommit 만 끊거다. (이 객체 한정)
            // 그러니 이 객체는 commit 이나 rollback 을 만나야 끝난다.
            // 원래의 기본값은 오토커밋이 true 가 기본이다. 기본적으로 켜져있으서 자동으로 된건데
            // 껐다는 얘기는 DB에 수동으로 입력을 해야한다는 뜻. 즉 commit 을 만나면 실행, rollback 을 만나면 실행이 안되고 돌아간다 라는 의미이다.
            // 각각의 메소드마다 커밋을 다르게 적용할 수 있다는 뜻.



            System.out.println( " autoCommit : " + con.getAutoCommit());

            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
