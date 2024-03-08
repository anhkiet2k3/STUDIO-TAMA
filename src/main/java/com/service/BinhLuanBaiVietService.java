package com.service;

import java.util.List;

import com.entity.BinhLuanBaiViet;

public interface BinhLuanBaiVietService {
	public List<BinhLuanBaiViet> findAll();

	public BinhLuanBaiViet findById(Integer id);

//	public List<SanPham> findByCategoryId(String cid) ;

	public BinhLuanBaiViet create(BinhLuanBaiViet bl);

	public BinhLuanBaiViet update(BinhLuanBaiViet bl);

	public void delete(Integer id);
}
