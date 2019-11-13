package com.guruframework.spring5recipeapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    @GetMapping({"","/","index"})
    public String getIndex(){
        log.debug("Getting index page");
        return "index";
    }
}
