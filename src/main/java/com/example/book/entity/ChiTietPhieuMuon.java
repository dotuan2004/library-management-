package com.example.book.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@Table(name = "chi_tiet_phieu_muon")
public class ChiTietPhieuMuon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChiTietPhieuMuon;

    @ManyToOne
    @JoinColumn(name = "id_phieu_muon", nullable = false)
    private PhieuMuon phieuMuon;

    @ManyToOne
    @JoinColumn(name = "id_sach", nullable = false)
    private Sach sach;



    // Constructors, getters, setters

    public ChiTietPhieuMuon() {
    }

    public ChiTietPhieuMuon(Long idChiTietPhieuMuon, PhieuMuon phieuMuon, Sach sach) {
        this.idChiTietPhieuMuon = idChiTietPhieuMuon;
        this.phieuMuon = phieuMuon;
        this.sach = sach;
    }

    public Long getIdChiTietPhieuMuon() {
        return idChiTietPhieuMuon;
    }

    public void setIdChiTietPhieuMuon(Long idChiTietPhieuMuon) {
        this.idChiTietPhieuMuon = idChiTietPhieuMuon;
    }

    public PhieuMuon getPhieuMuon() {
        return phieuMuon;
    }

    public void setPhieuMuon(PhieuMuon phieuMuon) {
        this.phieuMuon = phieuMuon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }
}

