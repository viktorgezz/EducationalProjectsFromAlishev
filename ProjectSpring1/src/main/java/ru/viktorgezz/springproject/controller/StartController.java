package ru.viktorgezz.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(produces = "text/plain;charset=UTF-8")
public class StartController {
    @GetMapping()
    public String start(){
        return "/start";
    }
}
