package com.example.demo.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.common.error.UnauthorizedException;
import com.example.demo.common.service.jwt.JwtService;
import com.example.demo.common.util.GetJwtToken;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter{
   @Autowired
   private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = GetJwtToken.getToken(request);

        System.out.println(token);
        if(token != null && jwtService.isUsable(token)){
            return true;
        }else{
            throw new UnauthorizedException();
        }

    }
}