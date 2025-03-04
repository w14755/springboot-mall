package com.eder.springbootmall.dao.impl;

import com.eder.springbootmall.dao.ProductDao;
import com.eder.springbootmall.dto.ProductQueryParams;
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
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        StringBuilder sql = new StringBuilder(
                "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date" +
                " FROM product WHERE 1 = 1");

        Map<String, Object> parameterMap = new HashMap<>();

        // 查詢條件
        if (productQueryParams.getCategory() != null) {
            // AND前需要加上空白，避免在組SQL的時候黏住
            sql.append(" AND category = :category");
            parameterMap.put("category", productQueryParams.getCategory().name());
        }

        if (productQueryParams.getSearch() != null) {
            sql.append(" AND product_name LIKE :search");
            parameterMap.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        // 排序
        sql.append(" ORDER BY ").append(productQueryParams.getOrderBy()).append(" ").append(productQueryParams.getSort());

        // 分頁
        sql.append(" LIMIT :limit OFFSET :offset");
        parameterMap.put("limit", productQueryParams.getLimit());
        parameterMap.put("offset", productQueryParams.getOffset());

        return namedParameterJdbcTemplate.query(sql.toString(), parameterMap, new ProductRowMapper());
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date "
                + "FROM product WHERE product_id = :productId";

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("productId", productId);

        List<Product> results = namedParameterJdbcTemplate.query(sql, parameterMap, new ProductRowMapper());
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Integer createProduct(ProductReq req) {
        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date)"
                + " VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> params = new HashMap<>();
        params.put("productName", req.getProductName());
        params.put("category", req.getCategory().name());
        params.put("imageUrl", req.getImageUrl());
        params.put("price", req.getPrice());
        params.put("stock", req.getStock());
        params.put("description", req.getDescription());
        params.put("createdDate", LocalDateTime.now());
        params.put("lastModifiedDate", LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey().intValue();
    }

    // Improved by standardizing variable names, removing debugging statements, and improving readability.

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

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
