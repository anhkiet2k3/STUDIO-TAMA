package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.BaiViet;

public interface BaiVietDAO extends JpaRepository<BaiViet, Integer> {

	@Query(value = "SELECT Top 2 * FROM Baiviet ORDER BY ngaydang DESC", nativeQuery = true)
	List<BaiViet> findTop2LatestBaidang();

	@Query(value = "SELECT Top 5 * FROM Baiviet ORDER BY ngaydang DESC", nativeQuery = true)
	List<BaiViet> findTop5LatestBaidang();

	@Query(value = "SELECT Top 1 * FROM Baiviet ORDER BY ngaydang DESC", nativeQuery = true)
	BaiViet findTop1LatestBaidang();

	@Query(value = "SELECT Top 4 * FROM Baiviet ORDER BY ngaydang DESC", nativeQuery = true)
	List<BaiViet> findTop4LatestBaidang();
}
