package com.yijun.libtest1;

public class Student extends Person {
    private String[] subjects;

    void print_subject(){
        System.out.println(name+" subject");
        for(int i=0; i<subjects.length; i++){
            System.out.println("subject : "+subjects[i]);
        }
    }

    public String[] getSubjects() {
        return subjects;
    }
    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }
}
