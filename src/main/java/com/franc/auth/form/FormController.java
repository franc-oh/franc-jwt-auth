package com.franc.auth.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

    /**
     * 메인 페이지 (인증없이 접근 가능)
     */
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    /**
     * 관리자 페이지 (인증필요, ADMIN 권한만 접근 가능)
     */
    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    /**
     * 회원 페이지 (인증필요, USER 이상의 권한 접근 가능)
     */
    @GetMapping("/user")
    public String user() {
        return "/user";
    }

}
