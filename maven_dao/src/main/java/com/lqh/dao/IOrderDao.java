package com.lqh.dao;

import com.lqh.domain.Orders;

import java.util.List;

public interface IOrderDao {
    public List<Orders> findAllOrder();

    void saveOrder(Orders orders);

    void deleteOrderById(Integer id);

    Orders findById(Integer id);

    void updateOrder(Orders orders);
}
