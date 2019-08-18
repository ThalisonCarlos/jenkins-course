package com.jenkins.course;

import com.jenkins.course.service.ServiceDev;
import com.jenkins.course.service.ServiceQA;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ServiceQATest {

    @InjectMocks
    private ServiceQA serviceQA;

    @Test
    public void getSuccess(){
        String serviceResponse = serviceQA.getSuccess();
        Assert.assertTrue(serviceResponse.contains("qa"));
    }

}
