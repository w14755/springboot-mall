package com.eder.springbootmall.rowmapper;

import com.eder.springbootmall.model.Product;
import com.eder.springbootmall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper  implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
        user.setLastModifiedDate(resultSet.getTimestamp("last_modified_date").toLocalDateTime());
        return user;
    }
}
