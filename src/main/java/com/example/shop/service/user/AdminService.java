package com.example.shop.service.user;

import com.example.shop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AdminService {
    Long total_money();

    Map<Product, Long> total_monetByProduct();
}
