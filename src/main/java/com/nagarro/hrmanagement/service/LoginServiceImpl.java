package com.nagarro.hrmanagement.service;

import com.nagarro.hrmanagement.model.User;
import com.nagarro.hrmanagement.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginRepository loginRepository;

    public User validateUser(User user){
        return loginRepository.validateUser(user);
    }

}
