package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BinhLuanBaiVietDAO;
import com.dao.BinhLuanLePhucDAO;
import com.entity.BinhLuanBaiViet;
import com.entity.BinhLuanLePhuc;
import com.service.BinhLuanBaiVietService;
import com.service.BinhLuanLePhucService;

@Service
public class BinhLuanBaiVietServiceImpl implements BinhLuanBaiVietService {
	@Autowired
	BinhLuanBaiVietDAO dao;

	public List<BinhLuanBaiViet> findAll() {
		return dao.findAll();
	}

	public BinhLuanBaiViet findById(Integer id) {
		return dao.findById(id).get();
	}

//	public List<SanPham> findByCategoryId(String cid) {
//		return dao.findByCategoryId(cid);
//	}

	public BinhLuanBaiViet create(BinhLuanBaiViet bl) {
		return dao.save(bl);
	}

	public BinhLuanBaiViet update(BinhLuanBaiViet bl) {
		return dao.save(bl);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
