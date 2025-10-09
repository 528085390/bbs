/*
package com.li.bbs.util;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@WebFilter(urlPatterns = "/*")
public class FilterUtil implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest Request = (HttpServletRequest) servletRequest;
        HttpServletResponse Response = (HttpServletResponse) servletResponse;
        String uri = Request.getRequestURI();
        if(uri.contains("/auth")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String token = Request.getHeader("token");
        if(token == null || token.isEmpty()){
            Response.setStatus(401);
            return;
        }
        if(jwtUtil.isTokenExpired(token)){
           Response.setStatus(401);
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
*/
