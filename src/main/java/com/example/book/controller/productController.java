package com.example.book.controller;

import com.example.book.entity.Sach;
import com.example.book.service.SachService;
import com.example.book.service.TacGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class productController {
    @Autowired
    private SachService sachService;
    @Autowired
    private TacGiaService tacGiaService;


    @GetMapping("/product")
    public String crud(Model model){
        Sach sach =new Sach();
        List<Sach> sachList =new ArrayList<Sach>();
        sachList=sachService.getAll();
        model.addAttribute("sachList",sachList);
        model.addAttribute("sach",sach);
        model.addAttribute("tacgiaList",tacGiaService.allTenTacGia());
        return "index/product";
    }


}
