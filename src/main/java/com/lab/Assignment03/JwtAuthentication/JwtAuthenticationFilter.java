package com.lab.Assignment03.JwtAuthentication;

import com.lab.Assignment03.Exception.UserException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil ; // Giả sử bạn đã tạo JwtUtil cho các thao tác với JWT
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7); // Lấy phần token sau "Bearer "

                String username = jwtUtil.extractUsername(token); // Extract username từ JWT

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Kiểm tra token có hợp lệ không
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                username, null, userDetails.getAuthorities()); // Tạo authentication token
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication); // Đặt authentication vào context
                    }

                }
            }

            filterChain.doFilter(request, response); // Tiếp tục chuỗi filter
        }
        catch (ExpiredJwtException ex){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"Token đã hết hạn, vui lòng đăng nhập lại!\"}");
        }
    }
}
