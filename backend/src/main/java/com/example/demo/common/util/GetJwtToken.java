package com.example.demo.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GetJwtToken {
   private static final String HEADER_AUTH = "Authorization";

    public static String getToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_AUTH); // Authorization 는 자기 토큰

        if(token == null){
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        System.out.println("쿠키 토큰");
                        token = cookie.getValue();
                    }
                }
            }
        }

        if(token==null){
            System.out.println("파라미터 토큰");
            token = request.getParameter("token");
        }

        return token;
    }
}
