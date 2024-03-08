package com.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.entity.BaiViet;
import com.entity.DatLich;
import com.entity.DichVu;
import com.entity.DonHang;

public interface DatLichService {
	public Page<DatLich> findAll(int page, int size);
	
	DatLich save(DatLich datLich);
	
	public boolean kiemTraThoiGian(String ngaythue, String thoigian);
	
	public DatLich findById(Integer id);

	public DatLich update(DatLich datlich);

	public void delete(Integer id);
}

