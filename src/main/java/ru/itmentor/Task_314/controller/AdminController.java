package ru.itmentor.Task_314.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
//    @RequestMapping("/")
//    public String rootRedir(){
//        return "redirect:/user";
//    }

    @RequestMapping("/login")
    public String formlogin() {
        return ("/login");
    }

    @RequestMapping("/admin")
    public String index() {
        return "admin";
    }
}
