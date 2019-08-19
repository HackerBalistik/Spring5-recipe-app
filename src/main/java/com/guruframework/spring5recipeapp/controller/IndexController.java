package com.guruframework.spring5recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/","index"})
    public String getIndex(){
        System.out.println("some message to Say.....Hi hi");
        return "index";
    }
}
