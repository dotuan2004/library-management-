package com.example.book.dao;

import com.example.book.entity.Sach;
import com.example.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SachRepo extends JpaRepository<Sach,Long> {
    public Sach findByTenSach(String username);
    public Sach findByIdSach(Long id);
    List<Sach> findByTenSachContainingIgnoreCase(String tenSach);

}
