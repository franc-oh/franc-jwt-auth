package com.franc.auth.member;

import com.franc.auth.member.entity.Member;
import com.franc.auth.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    final MemberRepository memberRepository;

    final PasswordEncoder passwordEncoder;

    public Member insertMember(Member member) {
        member.passwordEncode(passwordEncoder);
        log.info("======== Insert User Info {}", member.toString());
        return memberRepository.save(member);
    }


    /**
     * 인증/인가 DB 회원 풀로 연동
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. DAO의 findByUsername 쿼리메서드로 유저정보 가져오기
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Member Not Found : " + username));

        log.info("loadUserByUsername is {}", member.toString());

        // 2. (1)의 유저정보를 UserDetails 타입으로 리턴
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRole())
                .build();
    }
}
