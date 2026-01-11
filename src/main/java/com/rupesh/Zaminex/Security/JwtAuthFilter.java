package com.rupesh.Zaminex.Security;

import com.rupesh.Zaminex.Entity.User;
import com.rupesh.Zaminex.Repository.AuthRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AuthRepository authRepository;

    public JwtAuthFilter(JwtUtil jwtUtil, AuthRepository authRepository) {
        this.jwtUtil = jwtUtil;
        this.authRepository = authRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        String phone = jwtUtil.extractPhone(token);
        User user = authRepository.findByPhone(phone).orElse(null);

        if (user != null) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    user, null, List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
