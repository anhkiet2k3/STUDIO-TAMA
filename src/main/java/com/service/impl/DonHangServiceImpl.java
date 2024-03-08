package com.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.ChiTietDonHangDAO;
import com.dao.DonHangDAO;
import com.entity.ChiTietDonHang;
import com.entity.DonHang;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.DonHangService;

@Service
public class DonHangServiceImpl implements DonHangService {
	@Autowired
	DonHangDAO dao;

	@Autowired
	ChiTietDonHangDAO ctdao;

	@Override
	public DonHang findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public DonHang create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();

		DonHang donhang = mapper.convertValue(orderData, DonHang.class);
		
		dao.save(donhang);

		TypeReference<List<ChiTietDonHang>> type = new TypeReference<List<ChiTietDonHang>>() {
		};
		List<ChiTietDonHang> donhangs = mapper.convertValue(orderData.get("chitietdonhangs"), type).stream()
				.peek(d -> d.setDonhang(donhang)).collect(Collectors.toList());
		ctdao.saveAll(donhangs);

		return donhang;
	}

	@Override
	public List<DonHang> findAll() {

		return dao.findAll();
	}

	@Override
	public DonHang update(DonHang donhang) {
		return dao.save(donhang);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Page<DonHang> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Integer findSl(Integer i) {
		return dao.findSl(i);
	}

	@Override
	public Integer findTt(Integer i) {
		return dao.findTt(i);
	}

	@Override
	public List<DonHang> findByTrangThai(Optional<Integer> tt) {
		return dao.findByTrangThai(tt);
	}

	@Override
	public List<DonHang> findByIdKhachHang(String taikhoan) {
		return dao.findByIdKhachHang(taikhoan);
	}

	@Override
	public List<DonHang> findByIdKHvsTT(String taikhoan, Optional<Integer> tt) {
		return dao.findByIdKHvsTT(taikhoan, tt);
	}

	@Override
	public List<Object[]> getRevenueLast7Days() {
		return dao.getRevenueLast7Days();
	}

	@Override
	public Long getToDayOrder() {

		return dao.getTodayOrder();
	}

	@Override
	public Long totalOrder() {
		return dao.count();
	}

}
