package com.ohgiraffers.dection03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Applicatioon {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection con = getConnection();
        PreparedStatement stmt = null;
        int result = 0;
        Properties prop = new Properties();

        System.out.println( " 삭제를 원하시는 항목이 무엇인지 입력해주세요 *^^* : ");
        String a = sc.nextLine();

        try {
            prop.loadFromXML( new FileInputStream( "src/main/resources/mapper/menu-query.xml" ) );
            stmt = con.prepareStatement(prop.getProperty("deleteMenu"));
            stmt.setString(1, a);
            result = stmt.executeUpdate();


            if ( result == 1 ) {
                System.out.println(" 삭제에 성공하셨습니다 ~!~!   ༼ つ ◕_◕ ༽つ 👀🤦‍♀️🍔 ");
            }else{
                System.out.println( " 성공에 실패하셨습니다 ㅠㅠ    ¯\\_(ツ)_/¯ ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
        }


    }
}
