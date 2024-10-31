package com.example.book.controller;

import com.example.book.entity.GioHang;
import com.example.book.entity.NguoiMuon;
import com.example.book.entity.Sach;
import com.example.book.entity.SanPhamGioHang;
import com.example.book.service.CartItemService;
import com.example.book.service.CartService;
import com.example.book.service.NguoiMuonService;
import com.example.book.service.SachService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class giohangController {
    @Autowired
    NguoiMuonService nguoiMuonService;
    @Autowired
    CartItemService sanPhamGioHangService;
    @Autowired
    CartService cartService;
    @Autowired
    SachService sachService;
    @GetMapping("/getallcart")
    public String getAllCart(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        NguoiMuon nguoiMuonId = nguoiMuonService.getNguoiMuonIdByUserId(userId); // Giả định bạn có phương thức này

        GioHang cart = cartService.getGioHangByNguoiMuonId(nguoiMuonId.getIdNguoiMuon());
        Float total = 0.0f;
        if (cart != null) {
            // Lấy các mục trong giỏ hàng
            List<SanPhamGioHang> cartItems = cart.getItems();
            for (SanPhamGioHang item : cartItems) {
                total += item.getSoLuongSach() * item.getSach().getGiasach(); // Giả định bạn có phương thức getProduct()
            }

            model.addAttribute("cartItems", cartItems);

            model.addAttribute("total", total);
        } else {
            model.addAttribute("cartItems", "rong"); // Giỏ hàng trống
        }


        return "index/shopcart";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productId") Long sachId, HttpSession session , RedirectAttributes redirectAttributes) {
        Long userId = (Long) session.getAttribute("userId");
        Sach sach = sachService.timtheoid(sachId);

        if (userId != null && sachId != null) {
            NguoiMuon nguoiMuon = nguoiMuonService.getNguoiMuonIdByUserId(userId);
            if (nguoiMuon != null) {
                GioHang gioHang = cartService.getGioHangByNguoiMuonId(nguoiMuon.getIdNguoiMuon());

                if (gioHang == null) {
                    gioHang = new GioHang();
                    gioHang.setNguoiMuon(nguoiMuon);
                    gioHang.setItems(new ArrayList<>());
                    gioHang = cartService.save(gioHang); // Lưu giỏ hàng mới vào DB
                }

                // Duyệt qua danh sách để kiểm tra xem sản phẩm đã có trong giỏ hàng hay chưa
                boolean found = false;
                for (SanPhamGioHang item : gioHang.getItems()) {
                    if (item.getSach().getIdSach().equals(sachId)) {
                        // Nếu sản phẩm đã có, tăng số lượng
                        item.setSoLuongSach(item.getSoLuongSach() + 1);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    // Nếu sản phẩm chưa có, thêm mới vào giỏ hàng
                    SanPhamGioHang sanPhamGioHang = new SanPhamGioHang();
                    sanPhamGioHang.setSach(sach);
                    sanPhamGioHang.setSoLuongSach(1); // Giả định bắt đầu với số lượng 1
                    sanPhamGioHang.setGioHang(gioHang); // Thiết lập mối quan hệ với giỏ hàng
                    gioHang.getItems().add(sanPhamGioHang); // Thêm sản phẩm vào danh sách trong giỏ hàng
                }

                // Lưu lại giỏ hàng sau khi cập nhật
                cartService.save(gioHang); // Lưu giỏ hàng đã cập nhật
            }
        }
        redirectAttributes.addFlashAttribute("oke", "Thêm sản phẩm vào giỏ hàng thành công!");

        return "redirect:/product"; // Quay lại trang giỏ hàng
    }
    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam("productId") Long sachId, HttpSession session, RedirectAttributes redirectAttributes) {
        Long userId = (Long) session.getAttribute("userId");
        NguoiMuon nguoiMuon = nguoiMuonService.getNguoiMuonIdByUserId(userId);

        if (nguoiMuon != null) {
            GioHang gioHang = cartService.getGioHangByNguoiMuonId(nguoiMuon.getIdNguoiMuon());

            if (gioHang != null) {
                // Duyệt qua danh sách để tìm sản phẩm cần xóa
                SanPhamGioHang itemToRemove = null;
                for (SanPhamGioHang item : gioHang.getItems()) {
                    if (item.getSach().getIdSach().equals(sachId)) {
                        itemToRemove = item;
                        break;
                    }
                }

                if (itemToRemove != null) {
                    gioHang.getItems().remove(itemToRemove); // Xóa sản phẩm khỏi danh sách
                    cartService.save(gioHang); // Lưu giỏ hàng đã cập nhật
                    redirectAttributes.addFlashAttribute("oke", "Xóa sản phẩm khỏi giỏ hàng thành công!");
                }
            }
        }

        return "redirect:/getallcart"; // Quay lại trang giỏ hàng
    }


}
