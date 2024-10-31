package com.example.book.controller;

import com.example.book.entity.User;
import com.example.book.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class dangnhapcontroller {
    @Autowired
    UserService userService;
    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "dangky/login";
    }

    @GetMapping("/thanhcong")
    public String showPage(HttpSession session, @AuthenticationPrincipal UserDetails loggedInUser) {
        // Lấy đối tượng User từ service
        User user = userService.findByUsername(loggedInUser.getUsername());

        // Lưu ID người dùng vào session
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        return "index/home";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // Xóa thông tin người dùng trong session
        session.invalidate();

        // Đăng xuất người dùng
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);

        // Chuyển hướng về trang đăng nhập hoặc trang nào đó sau khi đăng xuất
        return "redirect:/login"; // Thay đổi URL theo nhu cầu
    }
    @Controller
    public class ErrorController {

        @GetMapping("/403")
        public String accessDenied() {
            return "index/ngoaile"; // Trả về trang 403.html
        }
    }
}
