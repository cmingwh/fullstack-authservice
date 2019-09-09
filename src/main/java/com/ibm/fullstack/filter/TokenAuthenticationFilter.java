package com.ibm.fullstack.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ibm.fullstack.entity.TokenAuthentication;

public class TokenAuthenticationFilter extends OncePerRequestFilter { 

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fc)
            throws ServletException, IOException {

        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
            // do nothing
        } else {
            Map<String, String[]> params = req.getParameterMap();
            if (!params.isEmpty() && params.containsKey("token")) {
                String token = params.get("token")[0];
                if (token != null) {
                    Authentication auth = new TokenAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            req.setAttribute("me.lotabout.springsecurityexample.security.TokenAuthenticationFilter.FILTERED", true); 
        }

        fc.doFilter(req, res); 
    }
}
