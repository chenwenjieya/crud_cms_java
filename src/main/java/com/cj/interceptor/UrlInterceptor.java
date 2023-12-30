package com.cj.interceptor;

import com.cj.common.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UrlInterceptor implements HandlerInterceptor {
    // 在请求处理之前进行调用（Controller方法调用之前）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----------preHandle----------");
        Boolean falg = false;
        // 校验token
        String token = request.getHeader("token");
        System.out.println(token);
        Claims claims = JwtUtil.verifyJwt(token);
        System.out.println(claims);

        if (claims != null) {
            falg = true;
        }

        return falg;
    }

    // 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("----------postHandle----------");
    }

    //  在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("----------afterCompletion----------");
    }
}
