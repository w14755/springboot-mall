package com.eder.springbootmall.dao;

import com.eder.springbootmall.dto.ProductReq;
import com.eder.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductReq req);

    void updateProduct(Integer productId, ProductReq req);
}
