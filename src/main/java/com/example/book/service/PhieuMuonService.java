package com.example.book.service;

import com.example.book.entity.NguoiMuon;
import com.example.book.entity.PhieuMuon;
import com.example.book.entity.Sach;

import java.util.List;

public interface PhieuMuonService {
    public List<PhieuMuon> getAll();
    void save(PhieuMuon phieuMuon);
    public PhieuMuon findphieumuon(NguoiMuon muon);
    public void delele(PhieuMuon phieuMuon);

}
