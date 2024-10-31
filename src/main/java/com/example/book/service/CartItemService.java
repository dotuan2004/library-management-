package com.example.book.service;

import com.example.book.entity.SanPhamGioHang;

import java.util.List;

public interface CartItemService {
    public List<SanPhamGioHang> getSanPhamGioHangByNguoiMuonId(Long nguoimuonId);
    public void save(SanPhamGioHang sanPhamGioHang);
}
