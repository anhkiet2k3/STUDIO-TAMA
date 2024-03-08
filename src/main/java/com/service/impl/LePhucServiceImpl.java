package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SanPhamDAO;
import com.entity.LePhuc;
import com.service.LePhucService;

@Service
public class LePhucServiceImpl implements LePhucService {
	@Autowired
	SanPhamDAO dao;

	public List<LePhuc> findAll() {
		return dao.findAll();
	}

	public LePhuc findById(Integer id) {
		return dao.findById(id).get();
	}

//	public List<SanPham> findByCategoryId(String cid) {
//		return dao.findByCategoryId(cid);
//	}

	public LePhuc create(LePhuc product) {
		return dao.save(product);
	}

	public LePhuc update(LePhuc product) {
		return dao.save(product);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Object[]> numberOfProductSoldByType() {
		return dao.numberOfProductSoldByType();
	}

	@Override
	public List<Object[]> getPercentByCate() {
		return dao.getPercentByCate();
	}

	@Override
	public List<Object[]> top10Product() {
		return dao.top10Product();
	}

	@Override
	public Long getTotalProduct() {
		return dao.count();
	}

}
