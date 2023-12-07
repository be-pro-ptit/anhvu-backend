package org.proptit.social_media.filter;

import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proptit.social_media.entity.AccountEntity;
import org.proptit.social_media.entity.UserEntity;
import org.proptit.social_media.exeption.NotFoundException;
import org.proptit.social_media.repository.UserRepository;
import org.proptit.social_media.service.jwt.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public AuthFilter(JwtService jwtService, UserRepository userRepository, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorization = request.getHeader("Authorization");
            if (authorization != null && authorization.startsWith("Bearer ")) {
                String token = authorization.substring(7);
                jwtService.validateAccessToken(token);
                AccountEntity accountEntity = jwtService.getAccountFromAccessToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(accountEntity.getUsername());
                UserEntity userEntity = userRepository.findByUserId(accountEntity.getUserId())
                                                      .orElseThrow(() -> new NotFoundException("User not found"));
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userEntity, userDetails.getAuthorities());
                SecurityContextHolder.getContext()
                                     .setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException expiredJwtException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter()
                    .write(new Gson().toJson("Token is expired"));
        }
    }

    @Override
    public void destroy() {

    }
}
