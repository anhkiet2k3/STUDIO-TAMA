package com.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.DichVu;

public interface DichVuDAO extends JpaRepository<DichVu, Integer>{
	
	@Query(value = "SELECT GIA FROM DICHVU WHERE id =?1", nativeQuery = true)
	DichVu findByGiaToID(Integer id);
	
	@Query(value = "SELECT id, ten, hinhanh1, gia, noidung, hinhanh2, mota, href\r\n"
			+ "FROM DICHVU WHERE id =?1", nativeQuery = true)
	DichVu finfByIdFormat(Integer id);
	
//	@Query(value = "SELECT GIA FROM DICHVU WHERE id =?1", nativeQuery = true)
//	Page<DichVu> findA2(int page, int size);
}
