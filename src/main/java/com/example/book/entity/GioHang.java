package com.example.book.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "gio_hang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "gioHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SanPhamGioHang> items; // Danh sách các mục trong giỏ hàng

    // Tham chiếu đến NguoiMuon
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_muon_id", nullable = false)
    private NguoiMuon nguoiMuon;

    // Constructors, getters, and setters
    public GioHang() {}

    public GioHang(Long id, List<SanPhamGioHang> items, NguoiMuon nguoiMuon) {
        this.id = id;
        this.items = items;
        this.nguoiMuon = nguoiMuon;
    }

    public GioHang(List<SanPhamGioHang> items, NguoiMuon nguoiMuon) {
        this.items = items;
        this.nguoiMuon = nguoiMuon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SanPhamGioHang> getItems() {
        return items;
    }

    public void setItems(List<SanPhamGioHang> items) {
        this.items = items;
    }

    public NguoiMuon getNguoiMuon() {
        return nguoiMuon;
    }

    public void setNguoiMuon(NguoiMuon nguoiMuon) {
        this.nguoiMuon = nguoiMuon;
    }
}
