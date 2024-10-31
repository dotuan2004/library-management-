package com.example.book.service;

import com.example.book.dao.GioHangRePo;
import com.example.book.entity.GioHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private GioHangRePo gioHangRepository;

    @Override
    public GioHang getGioHangByNguoiMuonId(Long nguoiMuonId) {
        return (GioHang) gioHangRepository.findByNguoiMuonId(nguoiMuonId);
    }
    public void addBookToCart(GioHang gioHang){
        gioHangRepository.save(gioHang);
    }

    @Override
    public GioHang save(GioHang gioHang) {
        return gioHangRepository.saveAndFlush(gioHang);
    }
}
