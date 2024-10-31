package com.example.book.service;

import com.example.book.entity.Tacgia;

import java.util.List;

public interface TacGiaService {
    public List<Tacgia> allTenTacGia();
    public void save(Tacgia tacgia);

}
