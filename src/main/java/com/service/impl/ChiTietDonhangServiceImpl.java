package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ChiTietDonHangDAO;
import com.service.ChiTietDonHangService;

@Service
public class ChiTietDonhangServiceImpl implements ChiTietDonHangService {

	@Autowired
	ChiTietDonHangDAO daoMua;

	@Override
	public Double getTodayIncome() {
		return null;
	}

	@Override
	public Double getTotalIncome() {
		return daoMua.findAll().stream().mapToDouble(item -> item.getGia() * item.getSoluong()).sum();

	}

}
