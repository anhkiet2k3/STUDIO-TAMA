package com.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.entity.BaiViet;

public interface BaiVietService {
	public Page<BaiViet> findAll(int page, int size, String sortBy);

	public BaiViet findById(Integer id);

	public List<BaiViet> findTop5LatestBaidang();

	public List<BaiViet> findTop2LatestBaidang();

	public BaiViet findTop1LatestBaidang();

	public Page<BaiViet> findAll(int page, int size);

	BaiViet save(BaiViet baiDang);

	void delete(Integer id);

	public List<BaiViet> findTop4LatestBaidang();
}