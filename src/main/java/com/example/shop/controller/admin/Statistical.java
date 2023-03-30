package com.example.shop.controller.admin;

import com.example.shop.model.product.Product;
import com.example.shop.service.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController("admin-statistical")
@RequestMapping("admin/statistical")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class Statistical {
    @Autowired
    private AdminService adminService;

    @GetMapping("total_money")
    public long total_money(){
        return adminService.total_money();
    }
    @GetMapping("total_moneyByProduct")
    public Map<Product, Long> total_moneyByproduct(){
        return adminService.total_monetByProduct();
    }

}
