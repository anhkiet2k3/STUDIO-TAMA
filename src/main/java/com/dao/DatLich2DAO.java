package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.DatLich;
import com.entity.DatLich2;
import com.entity.DichVu;
import com.entity.DonHang;

public interface DatLich2DAO extends JpaRepository<DatLich2, Integer> {

	@Query(value = "select *  from DATLICH where trangthai=?1 ORDER BY ngaythue desc", nativeQuery = true)
	List<DatLich2> findByTrangThai(Optional<Integer> tt);

	@Query(value = "SELECT DATLICH.id, DATLICH.tentaikhoan, DATLICH.iddichvu, DATLICH.ngaythue, DATLICH.thoigian, DATLICH.diachi, DATLICH.ghichu, DATLICH.cachnhan, DATLICH.trangthai\r\n"
			+ "FROM DATLICH \r\n"
			+ "JOIN TAIKHOAN ON DATLICH.tentaikhoan = TAIKHOAN.tentaikhoan\r\n"
			+ "WHERE TAIKHOAN.sdt like %?1% or TAIKHOAN.ten like %?1%", nativeQuery = true)
	List<DatLich2> findBySDT(String sdt);

	@Query(value="SELECT * FROM DATLICH WHERE tentaikhoan=?1 ORDER BY ngaythue desc",nativeQuery = true)
	List<DatLich2> findByIdKhachHang(String tentaikhoan);
}
