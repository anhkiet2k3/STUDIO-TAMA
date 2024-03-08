package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.TaiKhoan;

@Service
public interface TaiKhoanService {
	public List<TaiKhoan> findAll();

	public TaiKhoan findByTenTaikhoan(String tentaikhoan);

	public TaiKhoan create(TaiKhoan user);

	public TaiKhoan update(TaiKhoan user);

	public TaiKhoan updateUser(String email, String ten, String sdt, int id);

	List<TaiKhoan> getAdministrators();

	void deletebyId(String tentaikhoan);

	void deleteByTaikhoan(String tentaikhoan);

	List<Object[]> top10Customer();

	Long getTotalAccount();
}
