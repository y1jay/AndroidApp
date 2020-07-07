package com.yijun.libtest1;

public class Calculater {
    int cnt;

    void powerOn(){
        System.out.println("Power on");
    }
    //plus 3개의 정수를 입력받아서 합을 리턴하는 메소드
    int plus(int a, int b, int c){
        int result = a+b+c;
        return result;
    }
    //divide 두 정수를 입력받아서, 더블형을 리턴하는 함수
    double divide(int a, int b){
        double result = (double)a/b;
        return result;
    }

}
