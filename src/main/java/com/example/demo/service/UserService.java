package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    final int SUCCESSFUL_REGISTER=0;
    final int USER_EXISTS=1;
    final int WEAK_PASSWORD =2;


    @Autowired
    public UserRepository userRepository;

    public  List<User> findAll(){
        List<User> list = userRepository.findAll();
        System.out.println(list);
        return list;
    }

    public int registerUser(String userName, String userEmail,String password){
       boolean isRegisterAllowed = checkNameIsUnique(userName) && checkEmailIsUnique(userEmail);
       if(!isRegisterAllowed){
           return USER_EXISTS;
       }
       if(!checkPasswordStrenght(password)){
           return WEAK_PASSWORD;
       }
       String encryptedPassword=generateEncryptedPassword(password);
       Timestamp now = new Timestamp(System.currentTimeMillis());
       User user = new User();
       user.setUserEmail(userEmail);
       user.setUserName(userName);
       user.setRegistrationDate(now);
       user.setPassword(encryptedPassword);
       user.setFailedLoginAttempts(0);
       user.setLastLoginAttempt(now);
       userRepository.save(user);
       return SUCCESSFUL_REGISTER;
    }

    public String generateEncryptedPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword=passwordEncoder.encode(password);
        System.out.println(hashedPassword);
        return hashedPassword;
    }

    public boolean checkPasswordStrenght(String password){
        return password.length() > 8 && password.matches(".*[0-9]*.");
    }

    public boolean checkNameIsUnique(String userName){
        return userRepository.findByuserName(userName).isPresent();
    }

    public boolean checkEmailIsUnique(String email){
        return userRepository.findByuserEmail(email).isPresent();
    }

    public void updateUserOnFailedLogin(String user){
        User currentUser =userRepository.findByuserName(user).get();
        currentUser.setFailedLoginAttempts(currentUser.getFailedLoginAttempts()+1);
        currentUser.setLastLoginAttempt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(currentUser);
    }

    public void updateUserOnSuccessfulLogin(String user) {
        User currentUser =userRepository.findByuserName(user).get();
        currentUser.setFailedLoginAttempts(0);
        currentUser.setLastLoginAttempt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(currentUser);
    }
}
