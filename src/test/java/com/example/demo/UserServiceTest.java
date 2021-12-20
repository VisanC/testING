package com.example.demo;

import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void failWithWeakPassword(){
        Assert.assertFalse(userService.checkPasswordStrenght("weak"));
        Assert.assertFalse(userService.checkPasswordStrenght("weak1"));
        Assert.assertTrue(userService.checkPasswordStrenght("StrongStrong1"));
        Assert.assertFalse(userService.checkPasswordStrenght("StrongStrong"));
    }

    @Test
    public void generatedPasswordLooksCorrect(){
        String password="testpass";
        String encryptedPassword=userService.generateEncryptedPassword(password);
        Assert.assertTrue(encryptedPassword.length() ==60);
        Assert.assertTrue(encryptedPassword.charAt(0) == '$');
    }
}
