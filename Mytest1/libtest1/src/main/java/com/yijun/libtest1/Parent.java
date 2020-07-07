package com.yijun.libtest1;

public class Parent extends GrandParent {

    protected String job;


    Parent(){
        System.out.println("Parent Class");

    }


    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("name : "+name);
        System.out.println("age : "+age);
        System.out.println("job : "+job);
    }
}
