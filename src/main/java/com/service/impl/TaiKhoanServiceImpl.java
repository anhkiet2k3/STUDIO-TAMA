package com.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
	
import com.dao.QuyenDAO;
import com.dao.VaiTroDAO;
import com.dao.TaiKhoanDAO;
import com.entity.Quyen;
import com.entity.VaiTro;
import com.entity.TaiKhoan;
import com.service.TaiKhoanService;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
	@Autowired
	TaiKhoanDAO dao;

	@Autowired
	QuyenDAO aDao;

	@Autowired
	VaiTroDAO rDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	public List<TaiKhoan> findAll() {
		return dao.findAll();
	}

	public TaiKhoan create(TaiKhoan user) {
		user.setMatkhau(passwordEncode.encode(user.getMatkhau()));
		return dao.save(user);
	}

	@Override
	public TaiKhoan findByTenTaikhoan(String tentaikhoan) {
		return dao.findByTentaikhoan(tentaikhoan);
	}

	@Override
	public TaiKhoan update(TaiKhoan user) {
		user.setMatkhau(passwordEncode.encode(user.getMatkhau()));
		dao.save(user);
		return user;
	}

//	@Override
//	public TaiKhoan updateUser(String email, String ten, String sdt, int id) {
//		return dao.updateUser(email, ten, sdt, id);
//	}

	@Override
	public List<TaiKhoan> getAdministrators() {
		return dao.getAdministrators();
	}

	@Override
	public void deletebyId(String tentaikhoan) {
		dao.deleteById(tentaikhoan);

	}

	@Override
	public TaiKhoan updateUser(String email, String ten, String sdt, int id) {
		return null;
	}

	@Override
	public void deleteByTaikhoan(String tentaikhoan) {
		dao.deleteByTaikhoan(tentaikhoan);
		
	}

	@Override
	public List<Object[]> top10Customer() {
		
		return dao.top10Customer();
	}

	@Override
	public Long getTotalAccount() {
		return dao.count();
	}

}
