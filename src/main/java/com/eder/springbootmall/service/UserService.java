package com.eder.springbootmall.service;

import com.eder.springbootmall.dto.UserRegisterRq;
import com.eder.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRq rq);

    User getUserById(Integer userId);
}
