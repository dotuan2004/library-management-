package com.example.book.dao;

import com.example.book.entity.SanPhamGioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamGioHangRePo extends JpaRepository<SanPhamGioHang,Long> {
    @Query("SELECT s FROM SanPhamGioHang s WHERE s.gioHang.id = :gioHangId")
    List<SanPhamGioHang> findByGioHangId(@Param("gioHangId") Long gioHangId);
}
