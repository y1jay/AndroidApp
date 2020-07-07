package com.yijun.libtest1;

public class ClassTest_1 {
    private String name;
    private int salary;

    public ClassTest_1(){


    }

    ClassTest_1(String n, int s){
        name = n;
        salary = s;
    }
    public String getName(){
        return name;
    }
    public int getSalary(){
        return salary;
    }
    public void getInformation(){
        System.out.println("이름 : "+name + " 연봉은 : "+salary);
    }
    public void print(){
        System.out.println("슈퍼클래스");
    }


}
