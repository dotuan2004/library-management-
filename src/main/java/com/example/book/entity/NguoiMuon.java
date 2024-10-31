package com.example.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "nguoi_muon_sach")
public class NguoiMuon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNguoiMuon;

    @Column(name = "ten_nguoi_muon", nullable = false)
    private String tenNguoiMuon;

    @Column(name = "sdt")
    private String sdt;
    @Column(name = "so_luong_muon")
    private Integer soluongmuon;

    // Quan hệ 1-Nhiều với PhieuMuon
    @OneToMany(mappedBy = "nguoiMuon", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<PhieuMuon> phieuMuons;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    // Constructors, getters, setters
    @OneToOne(mappedBy = "nguoiMuon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private GioHang gioHang;

    public NguoiMuon() {
    }

    public NguoiMuon(Long idNguoiMuon, String tenNguoiMuon, String sdt, Integer soluongmuon, List<PhieuMuon> phieuMuons, User user, GioHang gioHang) {
        this.idNguoiMuon = idNguoiMuon;
        this.tenNguoiMuon = tenNguoiMuon;
        this.sdt = sdt;
        this.soluongmuon = soluongmuon;
        this.phieuMuons = phieuMuons;
        this.user = user;
        this.gioHang = gioHang;
    }

    public Long getIdNguoiMuon() {
        return idNguoiMuon;
    }

    public void setIdNguoiMuon(Long idNguoiMuon) {
        this.idNguoiMuon = idNguoiMuon;
    }

    public String getTenNguoiMuon() {
        return tenNguoiMuon;
    }

    public void setTenNguoiMuon(String tenNguoiMuon) {
        this.tenNguoiMuon = tenNguoiMuon;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getSoluongmuon() {
        return soluongmuon;
    }

    public void setSoluongmuon(Integer soluongmuon) {
        this.soluongmuon = soluongmuon;
    }

    public List<PhieuMuon> getPhieuMuons() {
        return phieuMuons;
    }

    public void setPhieuMuons(List<PhieuMuon> phieuMuons) {
        this.phieuMuons = phieuMuons;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GioHang getGioHang() {
        return gioHang;
    }

    public void setGioHang(GioHang gioHang) {
        this.gioHang = gioHang;
    }
}

