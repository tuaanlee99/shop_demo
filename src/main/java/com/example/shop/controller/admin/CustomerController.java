package com.example.shop.controller.admin;

import com.example.shop.model.user.Customer;
import com.example.shop.service.user.CustomerService;
import com.example.shop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/customers")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Customer> findAll(){
        return customerService.findAll();
    }
    @GetMapping("/enable")
    public List<Customer> findAllEnable(){
        return customerService.findAllEnable();
    }
    @GetMapping("/disable")
    public List<Customer> findAllDisable(){
        return customerService.findAllDisable();
    }
    @GetMapping("/active/{id}")
    public String activeCustomer(@PathVariable long id){
        return userService.active(id);
    }
    @GetMapping("/disable/{id}")
    public String disableCustomer(@PathVariable long id){
        return userService.disable(id);
    }



}
