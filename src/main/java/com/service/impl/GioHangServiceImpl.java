package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GioHangDAO;
import com.entity.GioHang;
import com.entity.LePhuc;
import com.entity.TaiKhoan;
import com.service.GioHangService;
import com.service.LePhucService;

@Service
public class GioHangServiceImpl implements GioHangService {
	@Autowired
	LePhucService lePhucService;

	@Autowired
	GioHangDAO gioHangDAO;

	@Autowired
	TaiKhoanServiceImpl taiKhoanServiceImpl;

	public GioHang addCart(String username, Integer idLePhuc) {
		TaiKhoan taiKhoan = taiKhoanServiceImpl.findByTenTaikhoan(username);
		if (taiKhoan == null)
			return null;

		LePhuc lePhuc = lePhucService.findById(idLePhuc);
		if (lePhuc == null)
			return null;

		GioHang gioHang = gioHangDAO.findByTaikhoanAndLephuc(taiKhoan, lePhuc);
		if (gioHang == null) {
			GioHang gh = new GioHang();
			gh.setLephuc(lePhuc);
			gh.setTaikhoan(taiKhoan);
			gh.setSoluong(1);
			return gioHangDAO.saveAndFlush(gh);
		} else {
			gioHang.setSoluong(gioHang.getSoluong() + 1);
			return gioHangDAO.saveAndFlush(gioHang);
		}

	}
	
	public GioHang update(Integer idCart , Integer soluong) {
		GioHang gioHang = gioHangDAO.findById(idCart).get();
		gioHang.setSoluong(soluong);
		
		return gioHangDAO.saveAndFlush(gioHang);

	}
	
	public void delete(Integer idCart) {
		gioHangDAO.deleteById(idCart);

	}
	@Transactional
	public void clear(String username) {
		TaiKhoan taiKhoan = taiKhoanServiceImpl.findByTenTaikhoan(username);
		if (taiKhoan == null) return;
		gioHangDAO.deleteAllByTenTaiKhoan(taiKhoan.getTentaikhoan());
	}


	public List<GioHang> getCartByUser(String username) {
		TaiKhoan taiKhoan = taiKhoanServiceImpl.findByTenTaikhoan(username);
		if (taiKhoan == null)
			return null;

		List<GioHang> gioHangs = gioHangDAO.findByTaikhoan(taiKhoan);
		return gioHangs;
	}

}
