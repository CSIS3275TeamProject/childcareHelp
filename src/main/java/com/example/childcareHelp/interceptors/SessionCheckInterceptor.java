package com.example.childcareHelp.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.childcareHelp.controller.LoginInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SessionCheckInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("this is interceptor, preHandle method");
        // TODO Auto-generated method stub
        HandlerMethod method = (HandlerMethod)handler;

        // Check Session
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo)session.getAttribute("account");
        if(loginInfo == null){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("this is interceptor, postHandle method");
    }
}