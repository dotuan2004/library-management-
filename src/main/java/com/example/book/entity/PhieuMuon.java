package com.example.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "phieu_muon")
public class PhieuMuon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhieuMuon;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_muon", nullable = false)
    private NguoiMuon nguoiMuon;

    @Column(name = "ngay_muon")
    private LocalDate ngayMuon;

    @Column(name = "ngay_tra_du_kien")
    private LocalDate ngayTraDuKien;

    // Quan hệ 1-Nhiều với ChiTietPhieuMuon
    @OneToMany(mappedBy = "phieuMuon", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ChiTietPhieuMuon> chiTietPhieuMuons;

    // Constructors, getters, setters

    public PhieuMuon() {
    }

    public PhieuMuon(Long idPhieuMuon, NguoiMuon nguoiMuon, LocalDate ngayMuon, LocalDate ngayTraDuKien, List<ChiTietPhieuMuon> chiTietPhieuMuons) {
        this.idPhieuMuon = idPhieuMuon;
        this.nguoiMuon = nguoiMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTraDuKien = ngayTraDuKien;
        this.chiTietPhieuMuons = chiTietPhieuMuons;
    }

    public PhieuMuon(NguoiMuon nguoiMuon, LocalDate ngayMuon, LocalDate ngayTraDuKien, List<ChiTietPhieuMuon> chiTietPhieuMuons) {
        this.nguoiMuon = nguoiMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTraDuKien = ngayTraDuKien;
        this.chiTietPhieuMuons = chiTietPhieuMuons;
    }

    public Long getIdPhieuMuon() {
        return idPhieuMuon;
    }

    public void setIdPhieuMuon(Long idPhieuMuon) {
        this.idPhieuMuon = idPhieuMuon;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public NguoiMuon getNguoiMuon() {
        return nguoiMuon;
    }

    public void setNguoiMuon(NguoiMuon nguoiMuon) {
        this.nguoiMuon = nguoiMuon;
    }

    public LocalDate getNgayTraDuKien() {
        return ngayTraDuKien;
    }

    public void setNgayTraDuKien(LocalDate ngayTraDuKien) {
        this.ngayTraDuKien = ngayTraDuKien;
    }

    public List<ChiTietPhieuMuon> getChiTietPhieuMuons() {
        return chiTietPhieuMuons;
    }

    public void setChiTietPhieuMuons(List<ChiTietPhieuMuon> chiTietPhieuMuons) {
        this.chiTietPhieuMuons = chiTietPhieuMuons;
    }
}

