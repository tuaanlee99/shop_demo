package com.example.shop.controller.customer;

import com.example.shop.model.user.Customer;
import com.example.shop.model.user.User;
import com.example.shop.service.user.CustomerService;
import com.example.shop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController("customer-information")
@RequestMapping("customer")
@PreAuthorize("hasAnyAuthority('CUSTOMER')")
public class information {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Customer findByID(@PathVariable long id){
        return customerService.findByID(id);
    }
    @GetMapping("{id}/update")
    public String updateCustomer(@PathVariable long id,@RequestBody User userDTO){
        return userService.updateUser(id, userDTO);
    }

}
