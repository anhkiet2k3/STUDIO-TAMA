package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.TaiKhoan;

public interface TaiKhoanDAO extends JpaRepository<TaiKhoan, String> {

	public TaiKhoan findByTentaikhoan(String tentaikhoan);

	@Query("select u from TaiKhoan u where u.email=:email")
	public TaiKhoan getbyEmail(@Param("email") String email);

	@Query("Select Distinct ar.taikhoan From Quyen ar where ar.role.id IN ('ADMIN')")
	List<TaiKhoan> getAdministrators();

	@Query(value = "DELETE from TaiKhoan  join DonHang d on TaiKhoan.tentaikhoan = DonHang.tentaikhoan where TaiKhoan.tentaikhoan = :tentaikhoan", nativeQuery = true)
	void deleteByTaikhoan(String tentaikhoan);

	@Query(value = "Select a.ten, a.email, a.sdt, " + "sum(odt.gia * odt.soluong) as totalPayment "
			+ "From TAIKHOAN a inner join DONHANG o on a.tentaikhoan = o.tentaikhoan "
			+ "inner join CHITIETDONHANG odt on o.id = odt.iddonhang " + "Group by a.ten, a.email, a.sdt "
			+ "order by totalPayment desc", nativeQuery = true)
	List<Object[]> top10Customer();
}
