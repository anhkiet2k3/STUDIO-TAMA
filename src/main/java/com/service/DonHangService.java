package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.entity.DonHang;
import com.fasterxml.jackson.databind.JsonNode;

public interface DonHangService {

	public List<DonHang> findByIdKhachHang(String tentaikhoan);

	public List<DonHang> findByIdKHvsTT(String tentaikhoan, Optional<Integer> tt);

	public DonHang findById(Integer id);

	public DonHang create(JsonNode orderData);

	public Page<DonHang> findAll(Pageable pageable);

	public DonHang update(DonHang donhang);

	public void delete(Integer id);

	public List<DonHang> findAll();

	public Integer findSl(Integer i);

	public Integer findTt(Integer i);

	public List<DonHang> findByTrangThai(Optional<Integer> tt);

	Long getToDayOrder();

	Long totalOrder();

	List<Object[]> getRevenueLast7Days();

}
