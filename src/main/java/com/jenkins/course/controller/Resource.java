package com.jenkins.course.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resource/")
public class Resource {

    @GetMapping
    public String getResource(){
        return "Success";
    }
}
