package com.service;

import java.util.List;

import com.entity.LePhuc;

public interface LePhucService {
	public List<LePhuc> findAll();

	public LePhuc findById(Integer id);

//	public List<SanPham> findByCategoryId(String cid) ;

	public LePhuc create(LePhuc sanpham);

	public LePhuc update(LePhuc sanpham);

	public void delete(Integer id);

	List<Object[]> numberOfProductSoldByType();

	List<Object[]> getPercentByCate();

	List<Object[]> top10Product();

	Long getTotalProduct();
}
