package com.eder.springbootmall.dao.impl;

import com.eder.springbootmall.dao.UserDao;
import com.eder.springbootmall.dto.UserRegisterRq;
import com.eder.springbootmall.model.User;
import com.eder.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRq rq) {
        StringBuilder sql = new StringBuilder("INSERT INTO user (email, password, created_date, last_modified_date) " +
                "VALUES (:email, :password, :createdDate, :lastModifiedDate)");

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("email", rq.getEmail());
        parameterMap.put("password", rq.getPassword());
        parameterMap.put("createdDate", LocalDateTime.now());
        parameterMap.put("lastModifiedDate", LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql.toString(), new MapSqlParameterSource(parameterMap), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public User getUserById(Integer userId) {
        StringBuilder sql = new StringBuilder("SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM user WHERE user_id = :userId");

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql.toString(), parameterMap, new UserRowMapper());

        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}
