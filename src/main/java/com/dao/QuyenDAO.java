package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Quyen;
import com.entity.TaiKhoan;


@Repository
public interface QuyenDAO extends JpaRepository<Quyen, Integer>{

	@Query("Select Distinct a From Quyen a where a.taikhoan IN ?1")
	List<Quyen> authoritiesOf(List<TaiKhoan> taikhoan);

	@Query("Select a From Quyen a where a.taikhoan.tentaikhoan like ?1")
	List<Quyen> getOneByRole(String taikhoan);
	
	@Transactional
	@Modifying
	@Query("Delete from Quyen where tentaikhoan = ?1")
	void deleteByTaikhoan(String tentaikhoan);

}
