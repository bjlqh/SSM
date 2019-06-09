package com.lqh.service;



import com.github.pagehelper.PageInfo;
import com.lqh.domain.Orders;

public interface IOrderService {

    public PageInfo<Orders> findAllOrder(Integer pageNum, Integer pageSize);

    void saveOrder(Orders orders);

    void deleteOrderById(Integer id);

    Orders findById(Integer id);

    void updateOrder(Orders orders);
}
