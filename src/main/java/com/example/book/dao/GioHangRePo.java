package com.example.book.dao;

import com.example.book.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GioHangRePo extends JpaRepository <GioHang,Long>{
    @Query("SELECT g FROM GioHang g WHERE g.nguoiMuon.idNguoiMuon = :nguoiMuonId")
    GioHang findByNguoiMuonId(@Param("nguoiMuonId") Long nguoiMuonId);
}
