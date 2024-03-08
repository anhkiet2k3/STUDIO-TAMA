package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.QuyenDAO;
import com.dao.TaiKhoanDAO;
import com.entity.Quyen;
import com.entity.TaiKhoan;
import com.service.QuyenService;

@Service
public class QuyenServiceImpl implements QuyenService {

	@Autowired
	private QuyenDAO authdao;
	@Autowired
	private TaiKhoanDAO accdao;

	@Override
	public List<Quyen> findAuthoritiesOfAdministrators() {
		List<TaiKhoan> accounts = accdao.getAdministrators();
		return authdao.authoritiesOf(accounts);
	}

	@Override
	public List<Quyen> findAll() {
		return authdao.findAll();
	}

	@Override
	public Quyen create(Quyen auth) {
		return authdao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authdao.deleteById(id);
	}

	@Override
	public List<Quyen> getOneByRole(String taikhoan) {
		return authdao.getOneByRole(taikhoan);
	}

	/* Summary */
	@Override
	public Long getTotalCustomer() {
		return authdao.findAll().stream().filter(e -> e.getRole().getTen().equals("Customers")).count();
	}

	@Override
	public void deleteByTaikhoan(String tentaikhoan) {
		authdao.deleteByTaikhoan(tentaikhoan);
		
	}

	

}
