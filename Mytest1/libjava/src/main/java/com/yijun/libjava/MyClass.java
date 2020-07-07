package com.yijun.libjava;

import java.util.Scanner;

public class MyClass {

    public static void main(String[] args) {
        int[] number_arr = {6, 7, -33, 123, -1, 0, 392, -742};
        int minus_cnt = 0;
        for (int i = 0; i < number_arr.length; i++) {
            if (number_arr[i] < 0) {
                minus_cnt = minus_cnt + 1;
            }
        }
        System.out.println("minus count : " + minus_cnt);
        for (int i = 7; i >= 0; i = i - 1) {
            System.out.print(number_arr[i] + " ");
        }

        System.out.println();
        System.out.println();
        int max = 0;
        for (int i = 0; i < number_arr.length; i++) {
            if (i == 0) {
                max = number_arr[i];
            } else {
                if (number_arr[i] > max) {
                    max = number_arr[i];
                }
            }
        }
        System.out.println(max);


    }
}

























