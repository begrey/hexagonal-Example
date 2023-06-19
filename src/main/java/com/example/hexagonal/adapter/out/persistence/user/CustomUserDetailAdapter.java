package com.example.hexagonal.adapter.out.persistence.user;

import com.example.hexagonal.application.port.out.user.LoadUserAuthPort;
import com.example.hexagonal.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
class CustomUserDetailAdapter implements UserDetailsService, LoadUserAuthPort {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public UserDetails loadUserByUsername(String midasUserId) throws UsernameNotFoundException {
        UserJpaEntity user = userRepository.findByMidasUserId(midasUserId);
        if(user == null){
            throw new UsernameNotFoundException(midasUserId);
        }
        return new CustomUserDetail(user);
    }

    // 인증객체 생성 여부 boolean
    @Transactional
    @Override
    public List<String> getUserRoles(String token) {
        UserDetails userDetails = loadUserByUsername(jwtTokenProvider.getUsernameFromToken(token));
        UserJpaEntity user = userRepository.findByMidasUserIdAndPassword(userDetails.getUsername(), userDetails.getPassword());
        List<String> userRoleList = user.getUserRoles().stream().map(role -> role.getUrl()).toList();
        return userRoleList;
    }

    @Override
    public UserDetails getUserDetailByUserName(String midasUserId) {
        return loadUserByUsername(midasUserId);
    }
}