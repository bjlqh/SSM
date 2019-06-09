package com.lqh.controller;

import com.lqh.domain.Product;
import com.lqh.service.IProductService;
import com.lqh.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @RequestMapping("/findAllProduct")
    public String findAll(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue ="3") Integer pageSize) {
        PageBean pb = iProductService.findAllPage(pageNum, pageSize);
        model.addAttribute("pb", pb);
        return "product/productList";
    }

    @RequestMapping("/addProduct")
    public String addProduct() {
        return "product/productAdd";
    }

    @RequestMapping("/saveProduct")
    public String saveProduct(Product product) throws Exception {
        iProductService.save(product);
        return "forward:/product/findAllProduct";
    }

    @RequestMapping("/updateProductUI")
    public String updateProductUI(Integer id, Model model) throws Exception {
        Product product = iProductService.findById(id);
        model.addAttribute("product", product);
        return "product/productUpdate";
    }

    @RequestMapping("/updateProduct")
    public String updateProduct(Product product) {
        iProductService.updateProduct(product);
        return "redirect:/product/findAllProduct";
    }

    @RequestMapping("/deleteById")
    public String deleteProductById(Integer id) {
        iProductService.deleteProductById(id);
        return "redirect:/product/findAllProduct";
    }

    @RequestMapping("/deleteByIds")
    public String deleteProductByIds(Integer[] ids) {
        if (ids != null && ids.length > 0) {
            for (Integer id : ids) {
                deleteProductById(id);
            }
        }
        return "redirect:/product/findAllProduct";
    }

}
