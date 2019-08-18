package com.jenkins.course;

import com.jenkins.course.controller.Resource;
import com.jenkins.course.service.ServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ResourceTest {

    @InjectMocks
    private Resource resource;

    @Mock
    ServiceInterface service;

    @Test
    public void getResource(){
        Mockito.when(service.getSuccess()).thenReturn("Success");
        String response = resource.getResource();
        Assert.assertNotNull(response);
    }

}
