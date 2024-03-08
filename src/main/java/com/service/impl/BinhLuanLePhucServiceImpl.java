package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BinhLuanLePhucDAO;
import com.entity.BinhLuanLePhuc;
import com.service.BinhLuanLePhucService;

@Service
public class BinhLuanLePhucServiceImpl implements BinhLuanLePhucService{
	@Autowired
	BinhLuanLePhucDAO dao;
	public List<BinhLuanLePhuc> findAll() {
		return dao.findAll();
	}
	
	public BinhLuanLePhuc findById(Integer id) {
		return dao.findById(id).get();
	}

//	public List<SanPham> findByCategoryId(String cid) {
//		return dao.findByCategoryId(cid);
//	}

	public BinhLuanLePhuc create(BinhLuanLePhuc bl) {
		return dao.save(bl);
	}

	public BinhLuanLePhuc update(BinhLuanLePhuc bl) {
		return dao.save(bl);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}
	

}
