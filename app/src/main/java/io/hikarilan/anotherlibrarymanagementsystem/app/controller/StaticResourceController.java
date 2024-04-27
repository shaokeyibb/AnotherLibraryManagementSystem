package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StaticResourceController {

    @GetMapping(value = "/{path:[^.]*}")
    public String redirect(@PathVariable String path) {
        return "forward:/";
    }

    @GetMapping("/")
    @ResponseBody
    public Resource index() {
        return new ClassPathResource("static/index.html");
    }
}
