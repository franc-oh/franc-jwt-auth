package com.franc.auth.form;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FormService {

    /**
     * SecurityContextHolder를 통해 인증정보가 공유되는 지 테스트
     */
    public void user() {
        // SecurityContextHolder를 통해 인증정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 실제 인증정보가 들어있는 Principal 가져오기
        Object principal = authentication.getPrincipal();

        // 권한에 대한 정보가 담겨있는 GrantedAuthority 가져오기
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // 인증여부 가져오기
        boolean authenticated = authentication.isAuthenticated();
    }

}
