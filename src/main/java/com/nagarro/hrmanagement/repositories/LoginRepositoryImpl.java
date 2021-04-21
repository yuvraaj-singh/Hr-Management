package com.nagarro.hrmanagement.repositories;

import com.nagarro.hrmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User validateUser(User user){
        String username = user.getUserName();
        String password = user.getPassword();
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM users u WHERE u.userName='"+username+"' AND u.password='"+password+"'",User.class);
        User result = query.getSingleResult();
        if(result!=null){
            return result;
        }
        return null;
    }
}
