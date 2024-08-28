package com.ohgiraffers.section02;

import com.ohgiraffers.section02.controller.MenuController;

import java.util.Scanner;

public class Application { // 사용자
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MenuController menucontroller = new MenuController(); // 1. MenuController() 이거로 한번 실행

        while (true) {
            System.out.println( " 사용할 기능을 선택 해주세요 *^^* : ");
            System.out.println( " 1. 가장 최신 메뉴 코드 조회");
            System.out.println( " 2. 모든 카테고리 목록 조회 ");
            System.out.println( " 3. 메뉴 등록 ");
            System.out.println( " 9. 프로그램 종료 ~! ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 : menucontroller.findMaxCode(); break; // findMaxCode() 라는 메소드 호출
                case 2 : menucontroller.selectcategory(); break;
                case 3 : menucontroller.insert();break;
                case 9 : return;
                default:
                    System.out.println( " 잘못된 입력 입니다. ㅜㅜ*^^* ");
                    break;

            }
        }
    }
}
