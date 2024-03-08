package com.service;

import java.util.List;

import com.entity.BinhLuanLePhuc;

public interface BinhLuanLePhucService {
public List<BinhLuanLePhuc> findAll() ;
	
	public BinhLuanLePhuc findById(Integer id) ;

//	public List<SanPham> findByCategoryId(String cid) ;

	public BinhLuanLePhuc create(BinhLuanLePhuc bl) ;

	public BinhLuanLePhuc update(BinhLuanLePhuc bl) ;

	public void delete(Integer id) ;
}
