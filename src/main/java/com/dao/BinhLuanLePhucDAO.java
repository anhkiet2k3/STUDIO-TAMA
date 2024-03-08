package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.BinhLuanLePhuc;

public interface BinhLuanLePhucDAO extends JpaRepository<BinhLuanLePhuc, Integer>{
//	@Query("SELECT p FROM BINHLUAN p WHERE p.idsanpham=?1")
//	List<BinhLuan> findbl(String a);
	@Query(value="SELECT * FROM BINHLUANLEPHUC WHERE idlephuc=?1",nativeQuery = true)
	List<BinhLuanLePhuc> findByIdbl(Integer id);
}
