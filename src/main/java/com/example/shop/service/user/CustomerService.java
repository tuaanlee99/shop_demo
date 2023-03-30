package com.example.shop.service.user;

import com.example.shop.model.user.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CustomerService {
    Customer findByID(long id);

    List<Customer> findAll();

    List<Customer> findAllEnable();

    List<Customer> findAllDisable();
}
