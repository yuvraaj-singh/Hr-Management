package com.nagarro.hrmanagement.repositories;

import com.nagarro.hrmanagement.model.User;

public interface LoginRepository {
    User validateUser(User user);
}
