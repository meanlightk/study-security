package com.study.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // View를 리턴한다!!
public class indexController {

    // localhost:8080
    @GetMapping("/")
    public String index() {
        // 머스테치 기본폴터 src/main/resources/
        // 뷰리졸버 설정: templates(prefix), mustache(suffix)
        return "index";
    }

}
