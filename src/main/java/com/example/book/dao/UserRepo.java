package com.example.book.dao;

import com.example.book.entity.NguoiMuon;
import com.example.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUsername(String username);


}
