package com.service;

import java.util.List;

import com.entity.Loai;

public interface LoaiService {
public List<Loai> findAll() ;
	
	public Loai findById(Integer id) ;

//	public List<SanPham> findByCategoryId(String cid) ;

	public Loai create(Loai loai) ;

	public Loai update(Loai loai) ;

	public void delete(Integer id) ;
}
