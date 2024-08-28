package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {
        /*
        * 1. DB 를 연결하는 개체를 만든다.
        * 2. 컴파일 시 미리 공간을 확보한다.
        * 3. 결과를 저장할 저장소를 만든다. ( 어떤 쿼리문인지에 따라 다름 )
        *
        * */
        // Connection 은  getConnection() 메서드를 통해 DB를 연결하는 역할을 수행한다.

        // ResultSet 에 Statement 가 전달한 쿼리를 실행시켜줌으로서 결과 값을 가져온다.
        // --> 실행하는 메서드는 executeQuery()로, 괄호 안에는 쿼리 문자열을 넣어준다.


        Connection con = getConnection(); // DB 를 연결하는 개체
        Properties prop = new Properties(); // 쿼리문을 가져와서 사용할꺼니까 먼저 선언이 필요하다.

        PreparedStatement pstmt1 = null; // 컴파일 시 미리 공간을 확보 (할당)
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        ResultSet rset1 = null; // select 문을 실행하여 테이블로부터 얻은 결과를 저장하고 있는 저장소
        ResultSet rset2 = null;
        List<Map<Integer, String>> categoryList = null; // 리스트 값을 여기에다가 담아줄꺼임

        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            // fileInputStream() 안에 파일 경로 작성 / 정보 읽어올 파일


            // 테이블의 내용을 불러오기 하는 명령 : load
            // 파일을 읽어서 콘솔에 출력해주는 명령 : FileInputStream

            String query = prop.getProperty("selectLastMenuCode"); // getProperty () 안에 쿼리의 키 값 작성
            String query2 = prop.getProperty("selectAllCategoryList");
            // getProperty : 정보를 얻어올 때 사용한다.

            pstmt1 = con.prepareStatement(query); // 실행 전 담아주려 만든 객체 ps에 쿼리문을 담음 !
            pstmt2 = con.prepareStatement(query2);

            rset1 = pstmt1.executeQuery(); // 전달받은 쿼리를 실행해 줄 코드 -> executeQuery()
            // 그냥 rset1 을 출력하려 하면 주솟값이 나올것이다. ( 클래스로 다뤘기때문에)

            if(rset1.next()){ // 결과값이 없으면 false 있으면 출력한다는 뜻임
                result = rset1.getInt("MAX(MENU_CODE)");
                // 우리가 조회할 컬럼 이름을 적는거다. 그 select 에서 조회할 컬럼수만큼 적어야 출력이 되는것이다.

            }
            System.out.println( " 최신 메뉴 코드 : " + result );

            rset2 = pstmt2.executeQuery(); // rset에 결과담음
            categoryList = new ArrayList<>();
            while(rset2.next()){
                Map<Integer, String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"), rset2.getString("CATEGORY_NAME"));
                categoryList.add(category);
                // getString => 해당 순서의 열에 있는 데이터를 string 형으로 받아 온다는 뜻이다.
            }
            System.out.println(" categoryList  = " + categoryList );

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            close(con);
            close(pstmt1);
            close(rset1);
            close(pstmt2);
            close(rset2);
            close(pstmt3);
            // 이렇게 사용한 개체들은 메모리에서 꼭 해제를 해야한다.
            // 해제 순서는 최근 사용한 개체부터 거꾸로 올라가면서 해제를 하면 된다. close() 사용

        }

    }
}
