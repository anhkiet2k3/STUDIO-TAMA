package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.entity.BaiViet;
import com.entity.DatLich;
import com.entity.DatLich2;
import com.entity.DichVu;
import com.entity.DonHang;

public interface DatLich2Service {
	public Page<DatLich2> findAll(int page, int size);

	public void delete(Integer id);

	public DatLich2 findById(Integer id);

	public List<DatLich2> findByTrangThai(Optional<Integer> tt);
	
	public List<DatLich2> findBySDT(String sdt);

	public List<DatLich2> findByIdKhachHang(String tentaikhoan);
	
}
