package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.DatLichDAO;
import com.dao.DichVuDAO;
import com.entity.BaiViet;
import com.entity.DatLich;
import com.entity.DichVu;
import com.entity.DonHang;
import com.service.DatLichService;
import com.service.DichVuService;

@Service
public class DatLichServiceImpl implements DatLichService {
	@Autowired
	DatLichDAO dao;

	@Override
	public DatLich save(DatLich datLich) {
		return dao.save(datLich);
	}

	@Override
	public Page<DatLich> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return dao.findAll(pageable);
	}

	@Override
	public boolean kiemTraThoiGian(String ngaythue, String thoigian) {
	    int soLichDaDat = dao.countByNgaythueAndThoigian(ngaythue, thoigian);
	    return soLichDaDat < 3; 
	}

	@Override
	public DatLich findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public DatLich update(DatLich datlich) {
		return dao.save(datlich);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}


}
