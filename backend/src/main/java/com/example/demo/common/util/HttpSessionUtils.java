package com.example.demo.common.util;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.User;

public class HttpSessionUtils {
   // 유틸 클래스는 보통 상수선언
    public static final String USER_SESSION_KEY = "me";

    public static boolean isLoginUser(HttpSession session){
        Object me = session.getAttribute(USER_SESSION_KEY);
        if(me == null){
            return  false;
        }
        return true;
    }

    public static User getUserFromSession(HttpSession session){
        if(!isLoginUser(session)){
            return null;
        }
        return (User) session.getAttribute(USER_SESSION_KEY);
    }
}