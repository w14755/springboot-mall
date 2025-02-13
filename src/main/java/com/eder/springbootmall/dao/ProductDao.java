package com.eder.springbootmall.dao;

import com.eder.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
