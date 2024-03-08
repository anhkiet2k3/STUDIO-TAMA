package com.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.entity.BaiViet;
import com.entity.DichVu;

public interface DichVuService {
	public Page<DichVu> findAll(int page, int size);

	public DichVu findById(Integer id);

	DichVu findByGiaToId(Integer id);

	public Page<DichVu> findAll5(int page, int size);

	DichVu save(DichVu dichVu);

	void delete(Integer id);

	public List<DichVu> findAll();

	DichVu findByIdFormat(Integer id);

	
}
