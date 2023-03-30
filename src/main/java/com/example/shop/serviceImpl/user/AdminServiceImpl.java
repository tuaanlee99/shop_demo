package com.example.shop.serviceImpl.user;

import com.example.shop.model.order.Bill;
import com.example.shop.model.product.*;
import com.example.shop.repository.order.BillRepository;
import com.example.shop.repository.product.*;
import com.example.shop.service.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Long total_money(){
        long total_money = 0;
        List<Bill> bills = billRepository.findAll();
        for (Bill bill: bills){
            total_money+=bill.getTotal_money();
        }
        return total_money;
    }

    @Override
    public Map<Product, Long> total_monetByProduct() {
        Map<Product, Long> map = new HashMap<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            long total_money = 0;
            for(ProductOrder productOrder: product.getProductOrders()){
                total_money+=productOrder.getNumber()*product.getPrice();
            }
            map.put(product, total_money);
        }
        return map;
    }
}
