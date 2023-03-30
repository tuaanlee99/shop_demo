package com.example.shop.serviceImpl.user;

import com.example.shop.exception.UserException;
import com.example.shop.model.user.Customer;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.service.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer findByID(long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new UserException("customer is not exit"));
        return customer;
    }

    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllEnable(){
        return customerRepository.findAllEnable();
    }
    @Override
    public List<Customer> findAllDisable(){
        return customerRepository.findAllDisable();
    }


}
