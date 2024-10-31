package com.example.book.service;

import com.example.book.dao.NguoiMuonRepo;
import com.example.book.entity.NguoiMuon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NguoiMuonServiceImpl implements NguoiMuonService{
    @Autowired
    private NguoiMuonRepo nguoiMuonRepository;
    @Override
    public NguoiMuon getNguoiMuonIdByUserId(Long userId) {
        return  nguoiMuonRepository.findNguoiMuonIdByUserId(userId);
    }
}
