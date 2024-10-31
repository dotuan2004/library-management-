package com.example.book.service;

import com.example.book.entity.Sach;

import java.util.List;

public interface SachService {
    public List<Sach> getAll();
    void save(Sach sach);
    public Boolean checktensach(String tensach);
    public Sach timtheoid(Long id);
    public void delete(Long id);
    public List<Sach> timKiemSach(String keyword);
}
