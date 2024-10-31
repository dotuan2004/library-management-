package com.example.book.dao;

import com.example.book.entity.NguoiMuon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NguoiMuonRepo extends JpaRepository<NguoiMuon,Long> {
    public NguoiMuon findByIdNguoiMuon(Long id);
    @Query("SELECT nm FROM NguoiMuon nm WHERE nm.user.id = :userId")
    public NguoiMuon findNguoiMuonIdByUserId(@Param("userId") Long userId);
}
