package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.ChiTietDonHang;


public interface ChiTietDonHangDAO extends JpaRepository<ChiTietDonHang, Integer>{
	@Query(value="SELECT TOP(4)* from chitietdonhang WHERE soluong>0 ORDER BY soluong DESC",nativeQuery = true)
	List<ChiTietDonHang> findByIdsp();
}
