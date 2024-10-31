package com.example.book.service;

import com.example.book.entity.GioHang;

public interface CartService {
    public GioHang getGioHangByNguoiMuonId(Long nguoiMuonId);
    public void addBookToCart(GioHang gioHang);
    public GioHang save(GioHang gioHang);
}
