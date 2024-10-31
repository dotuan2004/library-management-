package com.example.book.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "san_pham_gio_hang")
public class SanPhamGioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gio_hang_id", nullable = false)
    private GioHang gioHang; // Tham chiếu đến entity GioHang

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sach_id", nullable = false)
    private Sach sach; // Tham chiếu đến entity Sach

    @Column(name = "so_luong_trong_gio", nullable = false)
    private int soLuongSach; // Số lượng của sách trong giỏ

    // Constructors, getters, and setters
    public SanPhamGioHang() {}

    public SanPhamGioHang(Long id, GioHang gioHang, Sach sach, int soLuongSach) {
        this.id = id;
        this.gioHang = gioHang;
        this.sach = sach;
        this.soLuongSach = soLuongSach;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GioHang getGioHang() {
        return gioHang;
    }

    public void setGioHang(GioHang gioHang) {
        this.gioHang = gioHang;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }
}
