package com.eder.springbootmall.dto;

import com.eder.springbootmall.constant.ProductCategory;

public class ProductQueryParams {

    private ProductCategory category;
    private String search;
    private String orderBy = "created_date";
    private String sort = "desc";
    private Integer limit;
    private Integer offset;

    public ProductQueryParams() {
    }

    public ProductQueryParams(ProductCategory category, String search, String orderBy, String sort, Integer limit, Integer offset) {
        this.category = category;
        this.search = search;
        this.orderBy = orderBy;
        this.sort = sort;
        this.limit = limit;
        this.offset = offset;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
