package com.jenkins.course.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("qa")
public class ServiceQA implements ServiceInterface {
    @Override
    public String getSuccess() {
        return "Success Profile qa";
    }
}
