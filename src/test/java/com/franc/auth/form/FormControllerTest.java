package com.franc.auth.form;

import com.franc.auth.member.MemberService;
import com.franc.auth.member.entity.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FormControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberService memberService;


    /**
     * index 페이지에 미인증 유저도 접근이 가능해야된다.
     */
    @Test
    @WithAnonymousUser
    public void index_anonymous() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * admin 페이지에 ADMIN 권한이 아니면 열람 불가하다.
     */
    @Test
    @WithMockUser(username = "user1", roles = "USER")
    public void admin_another_role() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    /**
     * admin 페이지에 ADMIN 권한은 접근할 수 있다.
     */
    @Test
    @WithMockUser(username = "admin1", roles = "ADMIN")
    public void admin_success() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    /**
     * 폼-로그인 테스트
     */
    @Test
    @Transactional
    public void form_login() throws Exception {
        String username = "user";
        String password = "u123";
        String role = "USER";

        // 로그인 테스트 전 회원 등록
        Member member = memberService.insertMember(Member.builder()
                                                        .username(username)
                                                        .password(password)
                                                        .role(role)
                                                    .build());

        // 해당 유저를 폼-로그인을 통해 로그인 했을 때 잘 되는지?
        mockMvc.perform(formLogin().user(username).password(password))
                .andExpect(authenticated());

    }


}