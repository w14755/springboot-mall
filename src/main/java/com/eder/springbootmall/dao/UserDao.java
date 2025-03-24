package com.eder.springbootmall.dao;

import com.eder.springbootmall.dto.UserRegisterRq;
import com.eder.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRq rq);

    User getUserById(Integer userId);

}
