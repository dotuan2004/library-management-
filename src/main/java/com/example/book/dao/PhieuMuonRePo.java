package com.example.book.dao;

import com.example.book.entity.NguoiMuon;
import com.example.book.entity.PhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuMuonRePo extends JpaRepository<PhieuMuon,Long> {
    public PhieuMuon findByNguoiMuon(NguoiMuon nguoiMuon);
}
