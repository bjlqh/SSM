package com.lqh.dao;

import com.lqh.domain.Product;

import java.util.List;

public interface IProductDao {

    public Product findById(int id) throws Exception;

    void save(Product product) throws Exception;

    public List<Product> findAllPage(Integer pageNum, Integer pageSize);

    List<Product> findAll();

    void updateProduct(Product product);

    void deleteProductById(Integer id);

    int findCount();

}
