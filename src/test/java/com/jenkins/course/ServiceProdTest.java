package com.jenkins.course;

import com.jenkins.course.service.ServiceProd;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ServiceProdTest {

    @InjectMocks
    private ServiceProd serviceProd;

    @Test
    public void getSuccess(){
        String serviceResponse = serviceProd.getSuccess();
        Assert.assertTrue(serviceResponse.contains("prod"));
    }

}
