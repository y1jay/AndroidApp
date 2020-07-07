package com.yijun.libtest1;

public class MemberService {

    boolean login(String id, String password){
        if(id.compareTo("java")==0&&password.compareTo("12345")==0){
            return true;
        }else {
            return false;
        }
        }

    void logout(String id){
    System.out.println("Log Out");
    }
}
