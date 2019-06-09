package com.lqh.service.impl;

import com.lqh.dao.IProductDao;
import com.lqh.domain.Product;
import com.lqh.service.IProductService;
import com.lqh.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements IProductService {
    @Autowired
    private IProductDao iProductDao;

    @Override
    public Product findById(int id) throws Exception {
        return iProductDao.findById(id);
    }

    @Override
    public void save(Product product) throws Exception {
        iProductDao.save(product);
    }

    @Override
    public PageBean<Product> findAllPage(Integer pageNum, Integer pageSize) {
        PageBean<Product> pb = new PageBean<>();
        List<Product> list = iProductDao.findAllPage(pageNum, pageSize);
        pb.setList(list);
        pb.setPageNum(pageNum);
        pb.setPageSize(pageSize);
        int totalCount = iProductDao.findCount();
        pb.setTotalCount(totalCount);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public List<Product> findAll() {
        return iProductDao.findAll();
    }

    @Override
    public void updateProduct(Product product) {
        iProductDao.updateProduct(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        iProductDao.deleteProductById(id);
    }

}
