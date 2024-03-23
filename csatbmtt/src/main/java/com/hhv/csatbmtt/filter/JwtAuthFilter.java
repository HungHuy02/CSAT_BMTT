package com.hhv.csatbmtt.filter;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hhv.csatbmtt.dto.UserDTO;
import com.hhv.csatbmtt.entity.UserDetail;
import com.hhv.csatbmtt.entity.UserEntity;
import com.hhv.csatbmtt.service.UserService;
import com.hhv.csatbmtt.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService service;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization"); 
        String token = null; 
        String id = null; 
        if (authHeader != null && authHeader.startsWith("Bearer ")) { 
            token = authHeader.substring(7); 
            id = jwtUtil.extractId(token); 
        } 
  
        if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) { 
            UserDetails detail = service.loadUserByUsername(id); 
            if (jwtUtil.validateToken(token, detail)) { 
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(detail, null, null); 
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
                SecurityContextHolder.getContext().setAuthentication(authToken); 
            } 
        } 
        filterChain.doFilter(request, response); 
		
	}

}