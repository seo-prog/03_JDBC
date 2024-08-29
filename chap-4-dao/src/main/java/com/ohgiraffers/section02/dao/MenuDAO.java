package com.ohgiraffers.section02.dao;

import com.ohgiraffers.section02.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuDAO {
    // 데이터베이스와 연결될 클래스
    // 데이터 엑세스 오브젝트 - 데이터베이스와 상호작용을 할 클래스 / 쿼리들 날리는것들을 여기다가 다 모아둔다는 얘기

    private Properties prop = new Properties();

    public MenuDAO(String url) { // 생성자 menuDAO 를 객체로 만들어질때 url 을 무조건 받게 해뒀다는건,
        // 이렇게 입력해주면은 getprop 으로 꺼내올 수 있게 되는거다.
        try {
            prop.loadFromXML(new FileInputStream(url)); // 이 안에 들어갔으니
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 얘는 XML 파일을 쓰겠다는 뜻이다.
    }


    public int selectLastMenuCode(Connection con) { // 매개변수가 CONNECTION 타입임

        Statement stmt = null;
        ResultSet rset = null;
        int maxCode = 0; // 결과값 담아줄 maxCode

        String query = prop.getProperty("selectLastMenuCode");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()) {
                maxCode = rset.getInt("MAX(MENU_CODE)"); // int 자료형에 담에서 반환시킨다는 거임

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);

        }
        return maxCode;


    }

    public List<Map<Integer, String>> selectMenuName(Connection con) {

        Statement st = null; // 컴파일 시 담아둘 공간 생성
        ResultSet rs = null;  // resultset 은 jdbc 에서 제공하는 자료형인데, 쿼리값을 담을 수 있는 특수한 자료형 !
        // select 는 숫자 + 값 일케 섞여서 들어가야하기 때문에 ( 예시로는  getInt(CATEGORY_CODE) 로 뽑아내는 것과 같은)
        // 그렇기에 그냥 print 로 출력도 못하는 것이고, 단순 String rs 로 선언해서는 값을 담을수도 출력할수도 없는것이다 !! ( select 일 경우 !)

        List<Map<Integer, String>> selectlist =new ArrayList<>(); // 결과 담을 공간 만듬

        String query2 = prop.getProperty("selectAllCategoryList"); // 쿼리 담음

        try {
            st = con.createStatement();
            rs = st.executeQuery(query2);

            while ( rs.next()) { // rs.next는 (result 사진 쳐봐라) resultset 자료형을 한줄씩 출력해준다는 의미이다.
                Map<Integer, String> map = new HashMap<>();
                map.put(rs.getInt("CATEGORY_CODE"), rs.getString("CATEGORY_NAME"));
                selectlist.add(map); // map 을 리스트에다가 추가해 준다는 의미
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rs);
            close(st);

        }
        return selectlist;

    }

    public int insertMenu(Connection con, MenuDTO menuDTO) {
        PreparedStatement ps = null; // insert 는 미완성 쿼리이므로 PreparedStatement!!
        int result = 0;
        String query = prop.getProperty("insertMenu"); // 쿼리를 query에 담아줌

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, menuDTO.getName());
            ps.setInt(2, menuDTO.getPrice());
            ps.setInt(3, menuDTO.getCategoryCode());
            ps.setString(4, menuDTO.getStatus());
            result = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(" 잘못된 값이 입력됨 .. ~!~! *^^* ");
        } finally {
            close(con);
            close(ps);
        }
        return result;
    }

    public int updateMenu(Connection con, MenuDTO menuDTO, String menuname ) {
        PreparedStatement ps = null;
        int result = 0;
        String query = prop.getProperty("updateMenu");

        try {
            ps = con.prepareStatement(query);
            ps.setString(4, menuname);
            ps.setInt(2, menuDTO.getPrice());
            ps.setInt(3, menuDTO.getCategoryCode());
            ps.setString(1, menuDTO.getName());
            result = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(ps);
        }
        return result;

    }

    public int deleteMenu(Connection con, String menudelete) {

        PreparedStatement ps = null;
        int result = 0;
        String query = prop.getProperty("deletemenu");

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, menudelete);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(ps);

        }
        return result;
    }


}
