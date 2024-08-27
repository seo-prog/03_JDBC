package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        /*
        * 사용자가 원하는 메뉴를 등록할 수 있또록 만들어주세요.
        * 등록이 완료되면 성공, 실패하면 실패라고 출력 해 주세요.*/
        // 메뉴, 가격, 코드

        Scanner sc = new Scanner(System.in);

        Connection con = getConnection();
        PreparedStatement ps = null;
        int result = 0;

        Properties prop = new Properties();

        System.out.println( " 원하는 메뉴를 입력해주세요 *^^* : ");
        String namee = sc.nextLine();
        System.out.println( " 입력하신 메뉴의 가격을 입력해주세요 *^^* : ");
        double salary = sc.nextDouble();
        System.out.println( " 입력하신 메뉴의 코드를 입력해주세요 *^^* : ");
        int codee = sc.nextInt();
        sc.nextLine();
        System.out.println( " 입력하신 메뉴의 활성화 여부를 적어주세용 *^^* : ");
        String yn = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            ps = con.prepareStatement(prop.getProperty("insertMenu"));
            ps.setString(1, namee);
            ps.setDouble(2, salary);
            ps.setInt(3, codee);
            ps.setString(4, yn);

            result = ps.executeUpdate();

            if (result == 1) {
                System.out.println( " 등록이 완료 되셨씁니다 *^^* ");
            }else{
                System.out.println( " 등록 실패 ㅠㅠㅠㅠ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(ps);
        }


    }
}
