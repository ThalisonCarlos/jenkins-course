package com.jenkins.course;

import com.jenkins.course.service.ServiceDev;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ServiceDevTest {

    @InjectMocks
    private ServiceDev serviceDev;

    @Test
    public void getSuccess(){
        String serviceResponse = serviceDev.getSuccess();
        Assert.assertTrue(serviceResponse.contains("Dev"));
    }

}
