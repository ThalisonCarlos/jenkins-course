package com.jenkins.course.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ServiceProd implements ServiceInterface {
    @Override
    public String getSuccess() {
        return "Success Profile prod";
    }
}
