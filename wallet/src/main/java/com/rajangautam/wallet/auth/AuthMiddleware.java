package com.rajangautam.wallet.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.rajangautam.wallet.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthMiddleware extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null) {
            String[] authHeaderArr = authHeader.split("Bearer ");
            if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                String token = authHeaderArr[1];
                try {
                    Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
                    httpRequest.setAttribute("id", claims.get("id").toString());
                } catch (Exception e) {
                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid or expired token");
                    return;
                }
            } else {
                httpResponse.sendError(HttpStatus.BAD_REQUEST.value(), "Authorization token must be Bearer [token]");
                return;
            }
        } else {
            httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization token must be provided");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
