package com.eder.springbootmall.service;

import com.eder.springbootmall.dto.ProductReq;
import com.eder.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductReq req);
}
