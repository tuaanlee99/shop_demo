package com.example.shop.serviceImpl.user;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.exception.UserException;
import com.example.shop.jwt.JwtTokenProvider;
import com.example.shop.model.JwtResponse;
import com.example.shop.model.MyUserDetails;
import com.example.shop.model.user.Admin;
import com.example.shop.model.user.Customer;
import com.example.shop.model.user.User;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String sign_up(Customer userDTO){
        List<User> users = userRepository.findAll();
        for (User user: users){
            if (user.getUsername().equals(userDTO.getUsername())){
                return "user đã được sử dụng!";
            }
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setEnabled(false);
        userDTO.setRole();
        userRepository.save(userDTO);
        return "user được thêm thành công!";
    }

    @Override
    public String active(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not exist with id:" + id));
        user.setEnabled(true);
        return "active "+ user.getUsername()+" thành công!";
    }

    @Override
    public String disable(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not exist with id:" + id));
        user.setEnabled(false);
        return "disable "+ user.getUsername()+" thành công!";
    }
    @Override
    public JwtResponse log_in(String username, String password){
        User user = userRepository.getUserByUsername(username);
        if (user == null){
            throw new UserException("username không tồn tại!");
        }
        if(!user.isEnabled()){
            throw new UserException("user chưa active!");
        } else {
            Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            String jwt = tokenProvider.generateToken((MyUserDetails) authentication.getPrincipal());
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new JwtResponse(jwt, user.getUser_id(),user.getUsername(),user.getFullname(), authorities);
        }

    }

    @Override
    public String updateUser(long id, User userDTO){
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("user not exist with id:" + id));
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setBirthday(userDTO.getBirthday());
        user.setEmail(userDTO.getEmail());
        user.setPhone(user.getPhone());

        userRepository.save(user);
        return "update user thành công!";
    }


    @Override
    public String addAdmin(Admin userDTO){
        List<User> users = userRepository.findAll();
        for (User user: users){
            if (user.getUsername().equals(userDTO.getUsername())){
                return "user đã được sử dụng!";
            }
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setEnabled(true);
        userDTO.setRole();
        userRepository.save(userDTO);
        return "thêm admin thành công!";
    }
}
