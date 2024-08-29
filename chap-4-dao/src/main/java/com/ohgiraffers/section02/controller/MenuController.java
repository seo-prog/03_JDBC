package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.MenuDAO;
import com.ohgiraffers.section02.dto.MenuDTO;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection2;

public class MenuController { // 사용자의 명령을 입력받아서 DAO 한테 지시를 할꺼임



    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml"); // menudao 선언 !!
    // Application 에서 처음 선언하면서 MenuController 가 실행되게 되면서 이 menuDAO 가 실행
    // menuDAO 클래스 선언



    public void findMaxCode(){

        int result = menuDAO.selectLastMenuCode(getConnection2());// selectLastMenuCode 클래스의 매개변수가 CONNECTION 타입임
        System.out.println( "최신 메뉴 코드 : " + result );
    }

    public void selectcategory(){

        List<Map<Integer, String>> result2 = menuDAO.selectMenuName(getConnection2());

            System.out.println( " 카테고리 컬럼 조회 : *^^* : " + result2);;


    }

    public void insert() {
        Scanner sc = new Scanner(System.in);
        MenuDTO menu = new MenuDTO();
        System.out.println(" 메뉴 이름을 입력 해주세용 *^^* : ");
        menu.menuName(sc.nextLine());
        System.out.println(" 메뉴 가격을 입력 해수에요 ~! : ");
        menu.menuPrice(sc.nextInt());
        System.out.println(" 카테고리 번호를 입력 해주세요 ~!~!");
        menu.categoryCode(sc.nextInt());
        System.out.println(" 판매 여부를 등록 해주세요 ㅇ~! ");
        sc.nextLine();
        menu.status(sc.nextLine());

        int result = menuDAO.insertMenu(getConnection2(), menu);
        if (result > 0) { // 중복값이 있는 경우를 삭제, 삽입을 하게되면 그 값의 갯수만큼 result 값이 찍히게 된다.
            // 그러니까 9개가 등록된 옥수수 컬럼을 지우면 result 가 9가 나오므로 이런 if 문을 쓸때에는  result > 0 로 조건문을 작성해야 한다.
            System.out.println(" 메뉴 등록 완료 ~!");
        } else {
            System.out.println(" 메뉴 등록 실패 ~! ㅜㅠㅜㅠ");
        }
    }


        public void update(){

            MenuDTO menu = new MenuDTO();
            Scanner scr = new Scanner(System.in);

            System.out.println( " 수정하고픈 메뉴를 입력해주세요 *^^* 💕🤞🤞😁👍🙌 : ");
            String menuname = scr.nextLine();
            System.out.println( " 수정을 원하는 메뉴 이름을 입력해주세요 💕🤞🤞😁👍🙌: ");
            menu.menuName(scr.nextLine());
            System.out.println( " 수정을 원하는 가격을 입력해주세요 *^^* 💕🤞🤞😁👍🙌  : ");
            menu.menuPrice(scr.nextInt());
            System.out.println( " 수정을 원하시는 카테고리 번호를 입력해주세요 *^^* 💕🤞🤞😁👍🙌  : ");
            menu.categoryCode(scr.nextInt());

            int result = menuDAO.updateMenu(getConnection2(), menu, menuname);
                if (result > 0) {
                    System.out.println(" 메뉴 수정 완료 ~!");
                } else {
                    System.out.println(" 메뉴 수정 실패 ~! ㅜㅠㅜㅠ");
                }

        }

        public void menudelete(){

            Scanner scr = new Scanner(System.in);
            System.out.println( " 삭제하실 메뉴 이름을 입력해주세요 *^^* : ");
            String menuname = scr.nextLine();

            int result = menuDAO.deleteMenu(getConnection2(), menuname);
            if (result > 0) {
                System.out.println( " 메뉴 삭제 완료 ~! *^^* ");
            }else{
                System.out.println( " 메뉴 삭제 실패 ㅜㅜㅜㅜㅜ^!^");
            }


        }



    }





