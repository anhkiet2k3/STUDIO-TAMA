package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.DatLich2DAO;
import com.dao.DatLichDAO;
import com.dao.DichVuDAO;
import com.entity.BaiViet;
import com.entity.DatLich;
import com.entity.DatLich2;
import com.entity.DichVu;
import com.service.DatLich2Service;
import com.service.DatLichService;
import com.service.DichVuService;

@Service
public class DatLich2ServiceImpl implements DatLich2Service {
	@Autowired
	DatLich2DAO dao;

	@Override
	public Page<DatLich2> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return dao.findAll(pageable);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public DatLich2 findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public List<DatLich2> findByTrangThai(Optional<Integer> tt) {
		return dao.findByTrangThai(tt);
	}

	@Override
	public List<DatLich2> findBySDT(String sdt) {
		return dao.findBySDT(sdt);
	}

	@Override
	public List<DatLich2> findByIdKhachHang(String taikhoan) {
		return dao.findByIdKhachHang(taikhoan);
	}



	

}
