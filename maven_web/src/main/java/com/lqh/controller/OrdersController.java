package com.lqh.controller;

import com.github.pagehelper.PageInfo;
import com.lqh.domain.Orders;
import com.lqh.domain.Product;
import com.lqh.service.IOrderService;
import com.lqh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IProductService iProductService;

    @RequestMapping("/findAllOrder")
    public String findAllOrder(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "4") Integer pageSize) {
        PageInfo<Orders> pi = iOrderService.findAllOrder(pageNum, pageSize);
        model.addAttribute("pi", pi);
        /*SecurityContext securityContext = SecurityContextHolder.getContext();
        User user =(User)securityContext.getAuthentication().getPrincipal();
        System.out.println(user.getUsername());*/
        return "order/orderList";
    }

    @RequestMapping("/addOrder")
    public String addOrder(Model model) {
        List<Product> pList = iProductService.findAll();
        model.addAttribute("pList", pList);
        return "order/orderAdd";
    }

    @RequestMapping("/saveOrder")
    public String saveOrder(Orders orders) {
        iOrderService.saveOrder(orders);
        return "forward:/order/findAllOrder";
    }

    @RequestMapping("/updateOrderId")
    public String updateOrderId(Integer id, Model model) {
        Orders orders = iOrderService.findById(id);
        //model.addAttribute("orders",orders);
        return "order/orderUpdate";
    }

    @RequestMapping("/updateOrder")
    public String updateOrder(Orders orders) {
        iOrderService.updateOrder(orders);
        return "redirect:/order/findAllOrder";
    }

    @RequestMapping("/deleteById")
    public String deleteOrderById(Integer id) {
        iOrderService.deleteOrderById(id);
        return "forward:/order/findAllOrder";
    }

    @RequestMapping("/deleteByIds")
    public String deleteOrderByIds(Integer[] ids) {
        if (ids.length > 0) {
            for (Integer id : ids) {
                deleteOrderById(id);
            }
        }
        return "forward:/order/findAllOrder";
    }
}
