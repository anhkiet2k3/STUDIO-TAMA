package com.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.DatLich;
import com.entity.DichVu;

public interface DatLichDAO extends JpaRepository<DatLich, Integer> {
    List<DatLich> findByNgaythueAndThoigian(String ngaythue, String thoigian);
    
    @Query("SELECT COUNT(dl) FROM DatLich dl WHERE dl.dichvu = :dichvu AND dl.ngaythue = :ngaythue AND dl.thoigian = :thoigian")
    long countByDichvuAndNgaythueAndThoigian(@Param("dichvu") DichVu dichvu, @Param("ngaythue") String ngaythue, @Param("thoigian") String thoigian);

    @Query("SELECT MIN(dl.thoigian) FROM DatLich dl WHERE dl.ngaythue = :ngaythue")
    String findEarliestBookingTimeForDay(@Param("ngaythue") String ngaythue);
    
    int countByNgaythueAndThoigian(String ngaythue, String thoigian);
    
}

