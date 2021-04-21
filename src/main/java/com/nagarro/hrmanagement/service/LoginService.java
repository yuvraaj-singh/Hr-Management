package com.nagarro.hrmanagement.service;

import com.nagarro.hrmanagement.model.User;

public interface LoginService {
    User validateUser(User user);
}
