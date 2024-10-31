package com.example.book.service;

import com.example.book.dao.PhieuMuonRePo;
import com.example.book.entity.NguoiMuon;
import com.example.book.entity.PhieuMuon;
import com.example.book.entity.Sach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PhieuMuonServiceImpl implements PhieuMuonService{
    @Autowired
    PhieuMuonRePo phieuMuonRePo;
    @Override
    public List<PhieuMuon> getAll() {
        return phieuMuonRePo.findAll();
    }

    @Override
    public void save(PhieuMuon phieuMuon) {
        phieuMuonRePo.saveAndFlush(phieuMuon);
    }

    @Override
    public PhieuMuon findphieumuon(NguoiMuon nguoiMuon) {
        return phieuMuonRePo.findByNguoiMuon(nguoiMuon);
    }

    @Override
    public void delele(PhieuMuon phieuMuon) {
        phieuMuonRePo.delete(phieuMuon);
    }

}
