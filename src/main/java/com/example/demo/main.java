package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class main {
    @GetMapping("/")
    public String  run(){
        return "redirect:/question/list";
    }
}
