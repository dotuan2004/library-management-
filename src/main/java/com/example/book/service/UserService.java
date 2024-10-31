package com.example.book.service;

import com.example.book.entity.NguoiMuon;
import com.example.book.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    public User findByUsername(String username);


    public void save(User user);
    public void deleteUser(User user);
}

