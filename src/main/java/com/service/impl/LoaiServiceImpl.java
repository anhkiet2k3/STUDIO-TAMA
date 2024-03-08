package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.LoaiDAO;
import com.entity.Loai;
import com.service.LoaiService;

@Service
public class LoaiServiceImpl implements LoaiService{
	@Autowired
	LoaiDAO dao;
	public List<Loai> findAll() {
		return dao.findAll();
	}
	
	public Loai findById(Integer id) {
		return dao.findById(id).get();
	}

//	public List<SanPham> findByCategoryId(String cid) {
//		return dao.findByCategoryId(cid);
//	}

	public Loai create(Loai loai) {
		return dao.save(loai);
	}

	public Loai update(Loai loai) {
		return dao.save(loai);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}
	

}
