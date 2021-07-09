package com.franc.auth.member;

import com.franc.auth.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    final MemberService memberService;

    @GetMapping("/member/{username}/{password}/{role}")
    public Member insertMember(@ModelAttribute Member member) {
        log.info(" *** Insert User is {}", member.toString());
        return memberService.insertMember(member);
    }

}
