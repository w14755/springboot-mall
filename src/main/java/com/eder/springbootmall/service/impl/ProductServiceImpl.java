package com.eder.springbootmall.service.impl;

import com.eder.springbootmall.dao.ProductDao;
import com.eder.springbootmall.dto.ProductQueryParams;
import com.eder.springbootmall.dto.ProductReq;
import com.eder.springbootmall.model.Product;
import com.eder.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        return productDao.countProduct(productQueryParams);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductReq req) {
        return productDao.createProduct(req);
    }

    @Override
    public void updateProduct(Integer productId, ProductReq req) {
        productDao.updateProduct(productId, req);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }
}
