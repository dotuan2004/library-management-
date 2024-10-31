package com.example.book.controller;

import com.example.book.dao.*;
import com.example.book.entity.*;
import com.example.book.service.PhieuMuonService;
import com.example.book.service.SachService;
import com.example.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/manager")
public class quanlynguoimuonController {
    @Autowired
    private NguoiMuonRepo nguoiMuonRepository;
    @Autowired
    private SachRepo sachRepository;
    @Autowired
    private PhieuMuonRePo phieuMuonRePo;
    @Autowired
    private PhieuMuonService phieuMuonService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChiTietPhieuMuonRepo chiTietPhieuMuonRepository;

    @GetMapping("/nguoimuon")
    public String getAllNguoiMuon(Model model) {
        List<PhieuMuon> phieuMuons = phieuMuonService.getAll();
        // Danh sách để lưu thông tin người mượn và sách mượn
        List<Map<String, Object>> nguoiMuons = new ArrayList<>();
        for (PhieuMuon phieuMuon : phieuMuons) {
            for (ChiTietPhieuMuon chiTiet : phieuMuon.getChiTietPhieuMuons()) {
                // Tạo map để lưu thông tin
                Map<String, Object> nguoiMuonInfo = new HashMap<>();
                nguoiMuonInfo.put("idNguoiMuon", phieuMuon.getNguoiMuon().getIdNguoiMuon());
                nguoiMuonInfo.put("tenNguoiMuon", phieuMuon.getNguoiMuon().getTenNguoiMuon());
                nguoiMuonInfo.put("sdt", phieuMuon.getNguoiMuon().getSdt());
                nguoiMuonInfo.put("tenSach", chiTiet.getSach().getTenSach());
                nguoiMuonInfo.put("soLuong", phieuMuon.getNguoiMuon().getSoluongmuon());

                // Thêm vào danh sách
                nguoiMuons.add(nguoiMuonInfo);
            }
            model.addAttribute("nguoiMuons", nguoiMuons);

        }
        return "managesach/quanlynguoimuon";
    }
    @PostMapping("/themnguoi")
    public String themNguoiMuon(
            @RequestParam("tenNguoiMuon") String tenNguoiMuon,
            @RequestParam("sdt") String sdt,
            @RequestParam("tenSach") String tenSach,
            @RequestParam("soLuong") int soLuong,
            @RequestParam("soNgayMuon") int soNgayMuon,  // Nhận số ngày muốn mượn từ form
            Model model) {

        // Tìm sách theo tên sách
        Sach sach = sachRepository.findByTenSach(tenSach);
        if (sach == null) {
            model.addAttribute("error", "Sách không tồn tại!");
            return "error"; // Trang hiển thị lỗi
        }

        // Tạo NguoiMuon
        NguoiMuon nguoiMuon = new NguoiMuon();
        nguoiMuon.setTenNguoiMuon(tenNguoiMuon);
        nguoiMuon.setSdt(sdt);
        nguoiMuon.setSoluongmuon(soLuong);

        // Lưu NguoiMuon xuống database
        nguoiMuonRepository.save(nguoiMuon);

        // Tạo PhieuMuon
        PhieuMuon phieuMuon = new PhieuMuon();
        phieuMuon.setNguoiMuon(nguoiMuon);
        phieuMuon.setNgayMuon(LocalDate.now());

        // Tính ngày trả dự kiến dựa trên số ngày muốn mượn
        LocalDate ngayTraDuKien = LocalDate.now().plusDays(soNgayMuon);
        phieuMuon.setNgayTraDuKien(ngayTraDuKien);

        // Lưu PhieuMuon xuống database
        phieuMuonService.save(phieuMuon);

        // Tạo ChiTietPhieuMuon
        ChiTietPhieuMuon chiTietPhieuMuon = new ChiTietPhieuMuon();
        chiTietPhieuMuon.setPhieuMuon(phieuMuon);
        chiTietPhieuMuon.setSach(sach);


        // Lưu ChiTietPhieuMuon xuống database
        chiTietPhieuMuonRepository.save(chiTietPhieuMuon);

        return "redirect:/manager/nguoimuon";
    }

    @GetMapping("/updatenguoimuon")
    public String update(@RequestParam("id") Long id, Model model){
        NguoiMuon nguoiMuon= nguoiMuonRepository.findByIdNguoiMuon(id);
        model.addAttribute("nguoiMuon", nguoiMuon);
        return "managesach/editnguoimuon";
    }
    @PostMapping("/suanguoimuon")
    public String updateNguoiMuon(@ModelAttribute("nguoiMuon") NguoiMuon nguoiMuon,
                                  @RequestParam("soNgayMuon") int songaymuon,
                                  @RequestParam("tenSach") String tensach,
                                  Model model) {
        // Lấy thông tin người mượn từ cơ sở dữ liệu bằng id
        NguoiMuon existingNguoiMuon = nguoiMuonRepository.findByIdNguoiMuon(nguoiMuon.getIdNguoiMuon());

        if (existingNguoiMuon == null) {
            model.addAttribute("error", "ID người mượn không hợp lệ!");
            return "managesach/editnguoimuon";
        }

        // Cập nhật thông tin người mượn từ form
        existingNguoiMuon.setTenNguoiMuon(nguoiMuon.getTenNguoiMuon());
        existingNguoiMuon.setSoluongmuon(nguoiMuon.getSoluongmuon());
        existingNguoiMuon.setSdt(nguoiMuon.getSdt());

        // Lưu người mượn vào cơ sở dữ liệu


        // Lấy sách từ cơ sở dữ liệu, nếu không tìm thấy thì tạo mới sách
        Sach sach = sachRepository.findByTenSach(tensach);
        if (sach == null) {
            model.addAttribute("error", "tên sách không có!");
            return "managesach/editnguoimuon"; // Lưu sách trước khi sử dụng trong ChiTietPhieuMuon
        }
        nguoiMuonRepository.save(existingNguoiMuon);
        // Kiểm tra xem đã có phiếu mượn cho người mượn này chưa
        PhieuMuon phieuMuon = phieuMuonService.findphieumuon(existingNguoiMuon);
        if (phieuMuon == null) {
            // Nếu chưa có, tạo phiếu mượn mới
            phieuMuon = new PhieuMuon();
            phieuMuon.setNguoiMuon(existingNguoiMuon);
            phieuMuon.setNgayMuon(LocalDate.now());
        }

        // Cập nhật ngày trả dự kiến dựa trên số ngày mượn
        LocalDate ngayTraDuKien = LocalDate.now().plusDays(songaymuon);
        phieuMuon.setNgayTraDuKien(ngayTraDuKien);

        // Lưu hoặc cập nhật phiếu mượn
        phieuMuonService.save(phieuMuon);

        // Kiểm tra xem chi tiết phiếu mượn đã tồn tại hay chưa
        List<ChiTietPhieuMuon> chiTietPhieuMuonList = chiTietPhieuMuonRepository.findByPhieuMuonAndSach(phieuMuon, sach);
        if (chiTietPhieuMuonList.isEmpty()) {
            // Nếu chưa có, tạo chi tiết phiếu mượn mới
            ChiTietPhieuMuon chiTietPhieuMuon = new ChiTietPhieuMuon();
            chiTietPhieuMuon.setPhieuMuon(phieuMuon);
            chiTietPhieuMuon.setSach(sach);
            chiTietPhieuMuonRepository.save(chiTietPhieuMuon);
        } else {
            // Nếu có nhiều hơn một kết quả, xử lý logic tại đây
            // Hoặc chỉ cần cập nhật tất cả các chi tiết phiếu mượn
            for (ChiTietPhieuMuon chiTietPhieuMuon : chiTietPhieuMuonList) {
                // Cập nhật hoặc xử lý logic khác nếu cần
                chiTietPhieuMuonRepository.save(chiTietPhieuMuon);
            }
        }

        // Thêm thông báo thành công và chuyển hướng về trang danh sách người mượn
        model.addAttribute("success", "Người mượn và phiếu mượn đã được cập nhật thành công!");
        return "redirect:/manager/nguoimuon";
    }
    @GetMapping("/xoaNguoiMuon")
    public String xoaNguoiMuon(@RequestParam("id") Long id, Model model) {
        NguoiMuon nguoiMuon = nguoiMuonRepository.findByIdNguoiMuon(id);

        if (nguoiMuon != null) {




            nguoiMuonRepository.delete(nguoiMuon);
            model.addAttribute("success", "Người mượn và các thông tin liên quan đã được xóa thành công!");
        } else {
            model.addAttribute("error", "Không tìm thấy người mượn với ID đã cho!");
        }

        return "redirect:/manager/nguoimuon"; // Chuyển hướng về danh sách người mượn
    }



}
