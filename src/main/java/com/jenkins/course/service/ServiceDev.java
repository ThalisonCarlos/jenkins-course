package com.jenkins.course.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class ServiceDev implements ServiceInterface {
    @Override
    public String getSuccess() {
        return "Success Profile Dev";
    }
}
