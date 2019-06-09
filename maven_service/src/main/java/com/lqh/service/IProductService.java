package com.lqh.service;

import com.lqh.domain.Product;
import com.lqh.utils.PageBean;

import java.util.List;

public interface IProductService {

    public Product findById(int id) throws Exception;

    public void save(Product product) throws Exception;

    public PageBean findAllPage(Integer pageNumber, Integer pageSize);

    List<Product> findAll();

    void updateProduct(Product product);

    void deleteProductById(Integer id);

}
