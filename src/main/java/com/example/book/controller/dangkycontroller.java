package com.example.book.controller;

import com.example.book.dao.NguoiMuonRepo;
import com.example.book.dao.RoleRepo;
import com.example.book.entity.NguoiMuon;
import com.example.book.entity.Role;
import com.example.book.entity.User;
import com.example.book.service.UserService;
import com.example.book.web.RegisterUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/register")
public class dangkycontroller {

    UserService userService;
    RoleRepo roleRepository;
    BCryptPasswordEncoder passwordEncoder;
    NguoiMuonRepo nguoiMuonRepo;

    @Autowired
    public dangkycontroller(UserService userService, RoleRepo roleRepository, BCryptPasswordEncoder passwordEncoder, NguoiMuonRepo nguoiMuonRepo) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.nguoiMuonRepo = nguoiMuonRepo;
    }




    @GetMapping("/showRegisterForm")
    public String showRegisterForm(Model model){
        RegisterUser ru = new RegisterUser();
        model.addAttribute("registerUser", ru);
        return "dangky/register";
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("registerUser") RegisterUser registerUser,
                          BindingResult result,
                          Model model,
                          HttpSession session) {
        String username = registerUser.getUsername();

        // Form validation
        if (result.hasErrors()) {
            return "dangky/register";  // Trả về trang đăng ký nếu có lỗi
        }

        // Kiểm tra nếu user đã tồn tại
        User userExisting = userService.findByUsername(username);
        if (userExisting != null) {
            model.addAttribute("registerUser", new RegisterUser());
            model.addAttribute("my_error", "Tài khoản đã tồn tại!");
            return "dangky/register";  // Trả lại form đăng ký với lỗi
        }

        // Nếu chưa tồn tại, lưu user mới
        User user = new User();
        user.setUsername(registerUser.getUsername());

        // Mã hóa mật khẩu trước khi lưu
        String encodedPassword = passwordEncoder.encode(registerUser.getPassword());
        user.setPassword(encodedPassword);

        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());

        // Thêm vai trò mặc định cho người dùng
        Role defaultRole = roleRepository.findByName("ROLE_USER");
        Collection<Role> roles = new ArrayList<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        // Lưu người dùng vào cơ sở dữ liệu
        userService.save(user);

        // Tạo và lưu đối tượng NguoiMuon liên kết với user mới
        NguoiMuon nguoiMuon = new NguoiMuon();
        nguoiMuon.setTenNguoiMuon(user.getFirstName() + " " + user.getLastName());
        nguoiMuon.setUser(user);  // Liên kết NguoiMuon với User

        nguoiMuonRepo.save(nguoiMuon);  // Lưu NguoiMuon vào cơ sở dữ liệu

        // Cập nhật NguoiMuon trong User và lưu lại User
        user.setNguoiMuon(nguoiMuon);
        userService.save(user);

        // Đưa thông tin user vào session để sử dụng sau này
        session.setAttribute("myuser", user);

        // Chuyển hướng tới trang thành công
        return "dangky/login";
    }

}

