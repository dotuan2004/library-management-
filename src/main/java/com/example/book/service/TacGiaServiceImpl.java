package com.example.book.service;

import com.example.book.dao.TacgiaRepo;
import com.example.book.entity.Tacgia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TacGiaServiceImpl implements TacGiaService{
    @Autowired
    private TacgiaRepo tacgiaRepo;


    @Override
    @Transactional
    public List<Tacgia> allTenTacGia() {
        return tacgiaRepo.findAll();
    }

    @Override
    @Transactional
    public void save(Tacgia tacgia) {
        tacgiaRepo.save(tacgia);
    }


}
