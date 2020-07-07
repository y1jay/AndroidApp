package com.yijun.libtest1;

public class ClassTest_2 extends ClassTest_1 {

   public String department;

    ClassTest_2(String name, int salary,String department){
        super(name, salary);
        this.department=department;

    }


    @Override
    public void getInformation() {

        super.getInformation();
        System.out.println(" 부서는 : "+department);
    }
}

