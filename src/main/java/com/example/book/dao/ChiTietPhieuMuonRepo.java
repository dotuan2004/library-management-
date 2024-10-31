package com.example.book.dao;

import com.example.book.entity.ChiTietPhieuMuon;
import com.example.book.entity.PhieuMuon;
import com.example.book.entity.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChiTietPhieuMuonRepo extends JpaRepository<ChiTietPhieuMuon,Long> {
    public List<ChiTietPhieuMuon> findByPhieuMuonAndSach(PhieuMuon phieuMuon, Sach sach);
}
