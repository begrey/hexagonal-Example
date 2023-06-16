package com.example.hexagonal.global.security;

import com.example.hexagonal.adapter.out.persistence.user.JwtTokenProvider;
import com.example.hexagonal.global.enums.ErrorType;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private static final String JWT_PREFIX = "Bearer ";

    /*
        JWT 토큰 및 인증 객체 검증
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveTokenFromHeader(request);
        if (StringUtils.hasText(token)) {   // token 검증
            try {
                jwtTokenProvider.validateToken(token);
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (ExpiredJwtException expiredJwtException) {
                request.setAttribute("exception", ErrorType.EXPIRED_TOKEN);
            } catch (UnsupportedJwtException unsupportedJwtException) {
                request.setAttribute("exception", ErrorType.NOT_VALID_JWS_ARGUMENT);
            } catch (MalformedJwtException malformedJwtException) {
                request.setAttribute("exception", ErrorType.JWS_SIGNATURE_INVALID);
            } catch (UsernameNotFoundException usernameNotFoundException) {
                request.setAttribute("exception", ErrorType.NOT_FOUND_USER);
            }
        } else {
            request.setAttribute("exception", ErrorType.NOT_FOUND_TOKEN);
        }
        filterChain.doFilter(request, response);
    }

    // Request의 Header에서 token 값을 가져옵니다.
    public String resolveTokenFromHeader(HttpServletRequest req) {
        final String requestTokenHeader = req.getHeader("Authorization");
        if (requestTokenHeader != null && requestTokenHeader.startsWith(JWT_PREFIX)) {
            return requestTokenHeader.substring(7);
        }
        return null;
    }

}
