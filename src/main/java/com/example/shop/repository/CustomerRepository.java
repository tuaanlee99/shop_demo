package com.example.shop.repository;

import com.example.shop.model.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select customer from Customer customer where customer.isEnabled = true ")
    List<Customer> findAllEnable();

    @Query("select customer from Customer customer where customer.isEnabled = false ")
    List<Customer> findAllDisable();
}
