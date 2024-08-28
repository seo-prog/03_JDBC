package com.ohgiraffers.section02.dto;

import com.ohgiraffers.section02.dao.MenuDAO;

public class MenuDTO {

    private String name;

    private int price;

    private int categoryCode;

    private String status;




    public MenuDTO menuName(String name){
        this.name = name;
        return this; // return this 자신의 값을 넣는다.
    }

    public MenuDTO menuPrice(int price){
        if(price <= 0){
            System.out.println( " 음수가 입력됨 .. ");
        }else{
            this.price = price;
        }
        return this;
    }

    public MenuDTO categoryCode(int categoryCode){
        this.categoryCode = categoryCode;
        return this;
    }

    public MenuDTO status(String status){
        if( status.equals("예") || status.equals("Y")) {
            this.status = "Y";
        }else if(status.equals("아니오") || status.equals("N")){
            this.status = "N";
        }else{
            System.out.println( " 잘못 입력됨 ~~~ *^^* ~~~~~!");
        }
        return this;
    }



    public MenuDTO() {
    }

    public MenuDTO(String name, int price, int categoryCode, String status) {
        this.name = name;
        this.price = price;
        this.categoryCode = categoryCode;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", status='" + status + '\'' +
                '}';
    }
}
