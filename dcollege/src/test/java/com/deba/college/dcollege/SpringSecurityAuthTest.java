package com.deba.college.dcollege;

import com.deba.college.dcollege.entity.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringSecurityAuthTest {

    TestRestTemplate testRestTemplate;
    URL url;

    @Before
    public void setUp() throws MalformedURLException {
        testRestTemplate=new TestRestTemplate("test","test");
        url=new URL("http://localhost:9999/student/register");

    }

    @Test
    public void testAuth()
    {

       ResponseEntity<Student> responseEntity= testRestTemplate.getForEntity(url.toString(), Student.class);
       Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

    }

}
