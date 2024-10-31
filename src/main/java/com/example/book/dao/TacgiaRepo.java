package com.example.book.dao;

import com.example.book.entity.Tacgia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacgiaRepo extends JpaRepository<Tacgia,Long> {
    public Tacgia findByTenTacGia(String ten);
}
