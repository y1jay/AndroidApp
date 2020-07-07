package com.yijun.libtest1;

public class Car {
    String company = "Benz";
    String model = "S350";
    String color = "black";
    int maxSpeed =350;
    int speed;

    Car(int speed){
        this.speed= speed;

    }
    Car(String company, String model, String color){
        this.company=company;
        this.model= model;
        this.color=color;

    }

    void print(){
        System.out.println("회사 : "+company);
        System.out.println("모델 : "+model);
        System.out.println("색상 : "+color);
        System.out.println("최대속도 : "+maxSpeed);
        System.out.println("현재속도 : "+speed);

    }
}


