package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.DichVuDAO;
import com.entity.BaiViet;
import com.entity.DichVu;
import com.service.DichVuService;

@Service
public class DichVuServiceImpl implements DichVuService {
	@Autowired
	DichVuDAO dao;

	@Override
	public Page<DichVu> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return dao.findAll(pageable);
	}

	@Override
	public DichVu findById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public DichVu findByGiaToId(Integer id) {
		return dao.findByGiaToID(id);
	}

	@Override
	public Page<DichVu> findAll5(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return dao.findAll(pageable);
	}

	@Override
	public DichVu save(DichVu dichVu) {
		return dao.save(dichVu);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<DichVu> findAll() {
		return dao.findAll();
	}

	@Override
	public DichVu findByIdFormat(Integer id) {
		return dao.finfByIdFormat(id);
	}

}
