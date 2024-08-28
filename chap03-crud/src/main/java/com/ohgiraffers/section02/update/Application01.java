package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection con = getConnection();
        PreparedStatement ps = null;
        int result = 0;
        Properties props = new Properties();

        System.out.println( " 변경하실 메뉴 이름을 입력 해주세요 *^^* : ");
        String a = sc.nextLine();
        System.out.println( " 어떤 메뉴 이름으로 변경하실지 입력 해주세요 *^^*");
        String b = sc.nextLine();
        System.out.println( " 바꿀 메뉴의 가격을 입력 해주세요 *^^* : ");
        String c = sc.nextLine();
        System.out.println( " 바꿀 메뉴의 타입을 선택 해주세요 *^^* ");
        System.out.println( " 4. 한식, 5. 중식, 6. 일식, 7. 퓨전");
        String d = sc.nextLine();

        try {
            props.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            ps = con.prepareStatement(props.getProperty("updateMenu"));
            ps.setString(1, b);
            ps.setString(2, c);
            ps.setString(3, d);
            ps.setString(4, a);
            result = ps.executeUpdate();

            if(result ==  1){
                System.out.println( " 변경에 성공 *^^* ");
            }else{
                System.out.println( " 변경 실패 ㅜㅠㅜㅠ *^^* ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(ps);
        }


    }
}
