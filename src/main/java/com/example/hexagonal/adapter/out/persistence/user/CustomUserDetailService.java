package com.example.hexagonal.adapter.out.persistence.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String midasUserId) throws UsernameNotFoundException {
        UserJpaEntity user = userRepository.findByMidasUserId(midasUserId);
        if(user == null){
            throw new UsernameNotFoundException(midasUserId);
        }
        return new CustomUserDetail(user);
    }
}