package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.Quyen;

@Service
public interface QuyenService {
	List<Quyen> findAuthoritiesOfAdministrators();

	List<Quyen> findAll();

	Quyen create(Quyen auth);

	void delete(Integer id);

	List<Quyen> getOneByRole(String taikhoan);

	void deleteByTaikhoan(String tentaikhoan);

	Long getTotalCustomer();
	
}
