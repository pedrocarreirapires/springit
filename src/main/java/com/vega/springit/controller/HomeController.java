package com.vega.springit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request){
        model.addAttribute("message", "Hello World!");
        return "index";
    }

}
