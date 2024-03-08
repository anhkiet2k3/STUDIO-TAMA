package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.BinhLuanBaiViet;
import com.entity.BinhLuanLePhuc;

public interface BinhLuanBaiVietDAO extends JpaRepository<BinhLuanBaiViet, Integer> {
//	@Query("SELECT p FROM BINHLUAN p WHERE p.idsanpham=?1")
//	List<BinhLuan> findbl(String a);

	@Query(value = "SELECT * FROM binhluanbaiviet WHERE idbaiviet=?1", nativeQuery = true)
	List<BinhLuanBaiViet> findByIdbl(Integer id);
}
