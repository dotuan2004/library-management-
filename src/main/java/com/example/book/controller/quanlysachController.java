package com.example.book.controller;

import com.example.book.entity.Sach;
import com.example.book.entity.Tacgia;
import com.example.book.service.SachService;
import com.example.book.service.TacGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/manager")
public class quanlysachController {
    @Autowired
    private SachService sachService;
    @Autowired
    private TacGiaService tacGiaService;
    @GetMapping("/sach")
    public String crud(Model model){
        Sach sach =new Sach();
        List<Sach> sachList =new ArrayList<Sach>();
        sachList=sachService.getAll();
        model.addAttribute("sachList",sachList);
        model.addAttribute("sach",sach);
        model.addAttribute("tacgiaList",tacGiaService.allTenTacGia());
        return "managesach/quanlysach";
    }
    @PostMapping("/themsach")
    public String addsach(@ModelAttribute("sach") Sach sach, @RequestParam("tacGiaInput") String tacgiainput){
        Tacgia tacgia=new Tacgia();
        tacgia.setTenTacGia(tacgiainput);
        if(sachService.checktensach(sach.getTenSach())){
            throw new IllegalArgumentException("Tên sách không hợp lệ hoặc đã tồn tại!");
        }else {
            sach.getTacgias().add(tacgia); // Thêm tác giả vào sách
            tacgia.getSachs().add(sach);
            sachService.save(sach);
            tacGiaService.save(tacgia);
        }

        return "redirect:/manager/sach";
    }
    @PostMapping("/xoasach")
    public String deletesach(@RequestParam("sachId")  Long id){
        Sach s=sachService.timtheoid(id);
        if(s!=null){
            sachService.delete(id);
        }else {
            throw new IllegalArgumentException("id không hợp lệ hoặc đã tồn tại!");
        }
        return "redirect:/manager/sach";
    }
    @PostMapping("/suasach")
    public String updateSach(@ModelAttribute("sach") Sach sach,
                             @RequestParam("tacGiaInput") String tacgiainput,
                             Model model) {
        // Lấy sách từ cơ sở dữ liệu bằng id
        Sach existingSach = sachService.timtheoid(sach.getIdSach());

        // Nếu không tìm thấy sách thì trả về trang chỉnh sửa với thông báo lỗi
        if (existingSach == null) {
            model.addAttribute("error", "ID sách không hợp lệ!");
            return "managesach/editsach";
        }

        // Cập nhật thông tin sách từ form
        existingSach.setTenSach(sach.getTenSach());
        existingSach.setSoLuong(sach.getSoLuong());

        // Lấy danh sách tác giả cũ
        Set<Tacgia> existingTacgias = existingSach.getTacgias();

        // Nếu sách chưa có danh sách tác giả, tạo mới danh sách
        if (existingTacgias == null) {
            existingTacgias = new HashSet<>();
        }

        // Tạo danh sách các tác giả cần xóa
        List<Tacgia> tacgiasToRemove = new ArrayList<>();

        // Kiểm tra và lưu tác giả cũ cần xóa
        for (Tacgia tacgia : existingTacgias) {
            if (!tacgia.getTenTacGia().equals(tacgiainput)) {
                tacgiasToRemove.add(tacgia);
            }
        }

        // Xóa tác giả cũ khỏi sách và cơ sở dữ liệu
        for (Tacgia tacgia : tacgiasToRemove) {
            existingSach.getTacgias().remove(tacgia);
            tacgia.getSachs().remove(existingSach);
            tacGiaService.save(tacgia);
        }

        // Tạo mới đối tượng Tacgia với tên được nhập
        Tacgia newTacgia = new Tacgia();
        newTacgia.setTenTacGia(tacgiainput);

        // Thêm tác giả mới vào sách
        existingSach.getTacgias().add(newTacgia);
        newTacgia.getSachs().add(existingSach);

        // Lưu sách và tác giả vào cơ sở dữ liệu
        sachService.save(existingSach);
        tacGiaService.save(newTacgia);

        // Thêm thông báo thành công và chuyển hướng về trang danh sách sách
        model.addAttribute("success", "Sách đã được cập nhật thành công!");
        return "redirect:/manager/sach";
    }



    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model){
        Sach sach1=sachService.timtheoid(id);
        model.addAttribute("sach", sach1);
        return "managesach/editsach";
    }

    @GetMapping("/timkiem")
    public String timKiemSach(@RequestParam("keyword") String keyword, Model model) {
        List<Sach> ketQuaTimKiem = sachService.timKiemSach(keyword);
        model.addAttribute("sachList", ketQuaTimKiem);
        model.addAttribute("sach", new Sach());
        model.addAttribute("keyword", keyword);
        return "managesach/quanlysach"; // Tên của trang HTML
    }
}
