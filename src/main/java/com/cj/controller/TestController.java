package com.cj.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${text}")
    private String text;

    @GetMapping
    public String Test() {
        return "Hello World! HELLOW";
    }

    @GetMapping("/env")
    public String testEnv() {
        return text;
    }



}
