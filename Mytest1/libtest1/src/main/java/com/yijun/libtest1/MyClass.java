package com.yijun.libtest1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MyClass {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>();
        System.out.println(list.size());
        list.add("에베베");
        list.add("엘렐레");
        list.add("호롤롤로");
        System.out.println(list.size());
        list.add(1, "Python");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        list.remove(0);
        System.out.println("----------------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        list.remove("엘렐레");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        list.add("헬로");
        list.add("바이");
        list.add("웰컴");
        list.add("Welcome to this Java Class");
        list.add("Hello");
        list.add("Hello");
        System.out.println("------------------------");
        Iterator<String> i = list.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
        System.out.println("------------------------");
        for (String value : list) {
            System.out.println(value);
        }

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "Mike");
        map.put("tel", "010-1234-4567");
        map.put("address", "Seoul");
        map.put("name", "마이크");
        map.put("age", "25");

        System.out.println(map.get("name"));
        System.out.println(map.get("tel"));
        System.out.println(map.get("address"));

        System.out.println("-------------------");
        Object[] keys = map.keySet().toArray();

        for (int j = 0; j < keys.length; j++) {

            System.out.println((String) keys[j]);
        }
        System.out.println("-----------------");

        Object[] values = map.values().toArray();

        for (int q = 0; q < values.length; q++) {


            System.out.println((String) values[q]);

        }

        System.out.println("---------------");


        HashMap<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("KOR", 88);
        scoreMap.put("ENG", 77);
        scoreMap.put("MATH", 100);

        Object[] values2 = scoreMap.values().toArray();

        for (int w = 0; w < values2.length; w++) {


            System.out.println((Integer) values2[w]);

        }
        System.out.println("---------------");

        //ArrayList에 점수저장.
        //46.33, 77.8, 90.0, 87.5

        ArrayList<Double> list3 = new ArrayList<>();

        list3.add(46.33);
        list3.add(77.8);
        list3.add(90.0);
        list3.add(87.5);

        for (Double value2 : list3) {//3번루프<===list에 있는걸 하나씩 빼서 value에저장
            System.out.println(value2);


        }
        System.out.println("---------------");

        HashMap<String, Double> scoreMap2 = new HashMap<>();
        scoreMap2.put("Mike", 46.33);
        scoreMap2.put("Harry", 77.8);
        scoreMap2.put("Jane", 90.0);
        scoreMap2.put("Paul", 87.5);


        Object[] values1 = scoreMap2.values().toArray();


            System.out.println(scoreMap2.get("Jane"));

            scoreMap2.remove("Jane");
        System.out.println(scoreMap2.get("Jane"));


        }
    }





