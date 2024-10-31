package com.example.book.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tacgia")
public class Tacgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tac_gia")
    private Long idTacGia;
    @Column(name="ten_tac_gia")
    private String tenTacGia;
    @Column(name="quoc_tich")
    private String quocTich;

    @ManyToMany(mappedBy = "tacgias")
    Set<Sach> sachs = new HashSet<Sach>();

    public Tacgia() {
    }

    public Tacgia(Long idTacGia, String tenTacGia, String quocTich, Set<Sach> sachs) {
        this.idTacGia = idTacGia;
        this.tenTacGia = tenTacGia;
        this.quocTich = quocTich;
        this.sachs = sachs;
    }

    public Tacgia(String tenTacGia, String quocTich, Set<Sach> sachs) {
        this.tenTacGia = tenTacGia;
        this.quocTich = quocTich;
        this.sachs = sachs;
    }

    public Long getIdTacGia() {
        return idTacGia;
    }

    public void setIdTacGia(Long idTacGia) {
        this.idTacGia = idTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public Set<Sach> getSachs() {
        return sachs;
    }

    public void setSachs(Set<Sach> sachs) {
        this.sachs = sachs;
    }
}
