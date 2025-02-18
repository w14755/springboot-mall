package com.eder.springbootmall.dao.impl;

import com.eder.springbootmall.dao.ProductDao;
import com.eder.springbootmall.dto.ProductReq;
import com.eder.springbootmall.model.Product;
import com.eder.springbootmall.rowmapper.ProductRowMapper;
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
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size() > 0) {
            return productList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductReq req) {
        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date)" +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", req.getProductName());
        map.put("category", req.getCategory().name());
        map.put("imageUrl", req.getImageUrl());
        map.put("price", req.getPrice());
        map.put("stock", req.getStock());
        map.put("description", req.getDescription());
        map.put("createdDate", LocalDateTime.now());
        map.put("lastModifiedDate",  LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateProduct(Integer productId, ProductReq req) {
        String sql = "UPDATE product  SET product_name = :productName, category = :category, image_url = :imageUrl, price = :price, stock = :stock, description = :description, last_modified_date =:lastModifiedDate " +
                "WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productName", req.getProductName());
        map.put("category", req.getCategory().name());
        map.put("imageUrl", req.getImageUrl());
        map.put("price", req.getPrice());
        map.put("stock", req.getStock());
        map.put("description", req.getDescription());
        map.put("lastModifiedDate",  LocalDateTime.now());

        namedParameterJdbcTemplate.update(sql, map);
    }
}
