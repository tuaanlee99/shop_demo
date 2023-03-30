package com.example.shop.service.user;

import com.example.shop.model.JwtResponse;
import com.example.shop.model.user.Admin;
import com.example.shop.model.user.Customer;
import com.example.shop.model.user.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String sign_up(Customer user);

    String active(long id);

    String disable(long id);

    JwtResponse log_in(String username, String password);

    String updateUser(long id, User user);

    String addAdmin(Admin userDTO);
}
