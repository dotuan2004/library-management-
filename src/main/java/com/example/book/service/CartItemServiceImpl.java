package com.example.book.service;

import com.example.book.dao.SanPhamGioHangRePo;
import com.example.book.entity.GioHang;
import com.example.book.entity.SanPhamGioHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    private SanPhamGioHangRePo sanPhamGioHangRepository;
    @Autowired
    private CartService gioHangService;
    @Override
    public List<SanPhamGioHang> getSanPhamGioHangByNguoiMuonId(Long nguoiMuonId) {
        GioHang gioHang = gioHangService.getGioHangByNguoiMuonId(nguoiMuonId);
        return  sanPhamGioHangRepository.findByGioHangId(gioHang.getId());
    }

    @Override
    public void save(SanPhamGioHang sanPhamGioHang) {
         sanPhamGioHangRepository.save(sanPhamGioHang);
    }
}
