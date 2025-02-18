package com.eder.springbootmall.dao;

import com.eder.springbootmall.dto.ProductReq;
import com.eder.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();
    Product getProductById(Integer productId);

    Integer createProduct(ProductReq req);

    void updateProduct(Integer productId, ProductReq req);

    void deleteProduct(Integer productId);
}
