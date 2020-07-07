package com.yijun.libtest1;

public class GrandParent {
    protected String name;
    protected int age;

    public GrandParent() {
        System.out.println("GrandParentClass");
    }


        public void printInfo () {
            System.out.println("name : " + name);
            System.out.println("age : " + age);



    }
}
