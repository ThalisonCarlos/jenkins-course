package com.jenkins.course.controller;

import com.jenkins.course.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resource/")
@RequiredArgsConstructor
public class Resource {

    private final ServiceInterface service;

    @GetMapping
    public String getResource(){
        return service.getSuccess();
    }
}
