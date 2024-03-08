package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.GioHang;
import com.entity.LePhuc;
import com.entity.TaiKhoan;


@Repository
public interface GioHangDAO extends JpaRepository<GioHang, Integer>{
 GioHang findByTaikhoanAndLephuc(TaiKhoan taikhoan, LePhuc lephuc);
 List<GioHang> findByTaikhoan(TaiKhoan taikhoan);
 @Modifying
 @Query(value = "DELETE FROM GIOHANG WHERE TENTAIKHOAN = :tentaikhoan", nativeQuery = true)
 void deleteAllByTenTaiKhoan(String tentaikhoan);
}
