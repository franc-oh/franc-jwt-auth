package com.franc.auth.form;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FormController {

    final FormService formService;

    /**
     * 메인 페이지 (인증없이 접근 가능)
     */
    @GetMapping("/")
    public String index(Model model, Principal principal) {

        String message = "Hello";
        if(principal != null)
            message += " " + principal.getName();

        model.addAttribute("message", message);
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
    public String user(Model model, Principal principal) {
        formService.user();
        model.addAttribute("message", principal.getName());
        return "/user";
    }

}
