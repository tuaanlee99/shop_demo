package com.example.shop.controller.admin;

import com.example.shop.model.user.Admin;
import com.example.shop.model.user.User;
import com.example.shop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController("admin-information")
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class Information {
    @Autowired
    private UserService userService;

    @GetMapping("/admin_id = {id}/information")
    public String update(@PathVariable long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @GetMapping("add-admin")
    public String addAdmin(@RequestBody Admin user){
        return userService.addAdmin(user);
    }

}
