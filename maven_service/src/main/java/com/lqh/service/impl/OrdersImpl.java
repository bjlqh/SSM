package com.lqh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lqh.dao.IOrderDao;
import com.lqh.domain.Orders;
import com.lqh.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersImpl implements IOrderService {
    @Autowired
    private IOrderDao iOrderDao;

    @Override
    public PageInfo<Orders> findAllOrder(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = iOrderDao.findAllOrder();
        PageInfo<Orders> pi = new PageInfo<>(list);
        return pi;
    }

    @Override
    public void saveOrder(Orders orders) {
        iOrderDao.saveOrder(orders);
    }

    @Override
    public void deleteOrderById(Integer id) {
        iOrderDao.deleteOrderById(id);
    }

    @Override
    public Orders findById(Integer id) {
        return iOrderDao.findById(id);
    }

    @Override
    public void updateOrder(Orders orders) {
        iOrderDao.updateOrder(orders);
    }
}
