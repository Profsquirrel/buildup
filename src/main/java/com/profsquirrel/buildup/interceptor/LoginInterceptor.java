package com.profsquirrel.buildup.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoginInterceptor implements HandlerInterceptor  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
 
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("s_id");
        String teamName = (String)session.getAttribute("s_teamName");
        
        if (id == null) {
            // 로그인 페이지로 redirect
            response.sendRedirect("/");
            return false;
        }
        
        if(teamName == null) {
            response.sendRedirect("/team/addTeam");
            return false;
        }
        
        return true;
    }
}
