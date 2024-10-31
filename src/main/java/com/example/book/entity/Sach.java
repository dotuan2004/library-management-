package com.example.book.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sach")
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sach")
    private Long idSach;
    @Column(name="ten_sach")
    private String tenSach;
    @Column(name="nam_xuat_ban")
    private String namXuatBan;
    @Column(name="id_tac_gia")
    private Long idTacGia;
    @Column(name="so_luong")
    private String soLuong;
    @Column(name="gia_sach")
    private Float giasach;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="sachs_tacgias",
            joinColumns = @JoinColumn(name = "id_sach"),
            inverseJoinColumns = @JoinColumn(name="id_tac_gia")
    )
    Set<Tacgia> tacgias = new HashSet<Tacgia>();

    public Sach() {
    }

    public Sach(Long idSach, String tenSach, String namXuatBan, Long idTacGia, String soLuong, Float giasach, Set<Tacgia> tacgias) {
        this.idSach = idSach;
        this.tenSach = tenSach;
        this.namXuatBan = namXuatBan;
        this.idTacGia = idTacGia;
        this.soLuong = soLuong;
        this.giasach = giasach;
        this.tacgias = tacgias;
    }

    public Long getIdSach() {
        return idSach;
    }

    public void setIdSach(Long idSach) {
        this.idSach = idSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(String namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public Long getIdTacGia() {
        return idTacGia;
    }

    public void setIdTacGia(Long idTacGia) {
        this.idTacGia = idTacGia;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiasach() {
        return giasach;
    }

    public void setGiasach(Float giasach) {
        this.giasach = giasach;
    }

    public Set<Tacgia> getTacgias() {
        return tacgias;
    }

    public void setTacgias(Set<Tacgia> tacgias) {
        this.tacgias = tacgias;
    }
}
