package com.eder.springbootmall.service.impl;

import com.eder.springbootmall.dao.UserDao;
import com.eder.springbootmall.dto.UserRegisterRq;
import com.eder.springbootmall.model.User;
import com.eder.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRq rq) {
        return userDao.createUser(rq);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

}
