package com.example.book.service;

import com.example.book.dao.SachRepo;
import com.example.book.entity.Sach;

import com.example.book.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SachServiceImpl implements SachService{
    private SachRepo sach;

    @Autowired
    public SachServiceImpl(SachRepo sach) {
        this.sach = sach;
    }

    @Override
    @Transactional
    public List<Sach> getAll() {
        return sach.findAll();
    }

    @Override
    @Transactional
    public void save(Sach s) {

            sach.saveAndFlush(s);

    }

    @Override
    @Transactional
    public Boolean checktensach(String tensach) {
        Sach s = sach.findByTenSach(tensach.trim());
        return s != null; // Trả về true nếu sách đã tồn tại
    }

    @Override
    @Transactional
    public Sach timtheoid(Long id) {
        return sach.findByIdSach(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sach.deleteById(id);
    }

    @Override
    public List<Sach> timKiemSach(String keyword) {
        return sach.findByTenSachContainingIgnoreCase(keyword);
    }
}
