package com.yijun.emplo2.list;

public class Employee {
    private int id;
    private String Name;
    private int age;
    private int salary;
    public Employee(){

    }

    public Employee(int id, String name, int age, int salary) {
        this.id = id;
        Name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

