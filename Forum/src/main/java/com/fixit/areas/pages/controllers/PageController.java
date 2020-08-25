package com.fixit.areas.pages.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fixit.abstractions.controller.BaseController;

@Controller
public class PageController extends BaseController {

    @GetMapping("/")
    public String index() {
        return "/views/home";
    }

    @GetMapping("/contact")
    public ModelAndView contactPage() {
        return super.view("views/contact", "Contact");
    }

    @GetMapping("/about")
    public ModelAndView aboutPage() {
        return super.view("views/about", "About");
    }
}