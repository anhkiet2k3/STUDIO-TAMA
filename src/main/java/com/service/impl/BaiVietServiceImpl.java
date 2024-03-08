package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dao.BaiVietDAO;
import com.entity.BaiViet;

import com.service.BaiVietService;

@Service
public class BaiVietServiceImpl implements BaiVietService {
	@Autowired
	BaiVietDAO dao;

	@Override
	public Page<BaiViet> findAll(int page, int size, String sortBy) {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy).descending());
		return dao.findAll(pageable);
	}

	@Override
	public BaiViet findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public List<BaiViet> findTop5LatestBaidang() {
		return dao.findTop5LatestBaidang();
	}

	@Override
	public List<BaiViet> findTop2LatestBaidang() {
		return dao.findTop2LatestBaidang();
	}

	@Override
	public BaiViet findTop1LatestBaidang() {
		return dao.findTop1LatestBaidang();
	}

	@Override
	public Page<BaiViet> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return dao.findAll(pageable);
	}

	@Override
	public BaiViet save(BaiViet baiDang) {
		return dao.save(baiDang);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<BaiViet> findTop4LatestBaidang() {
		return dao.findTop4LatestBaidang();
	}

}
