package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.DonHang;

public interface DonHangDAO extends JpaRepository<DonHang, Integer>{

	@Query(value="SELECT * FROM donhang WHERE tentaikhoan=?1 ORDER BY ngaytao desc",nativeQuery = true)
	List<DonHang> findByIdKhachHang(String tentaikhoan);

	@Query(value="select count(trangthai)  from DONHANG where trangthai=?1",nativeQuery = true)
	Integer findSl(Integer i);

	@Query(value="select sum(tongtien)  from DONHANG where trangthai=?1",nativeQuery = true)
	Integer findTt(Integer i);

	@Query(value="select *  from DONHANG where trangthai=?1 ORDER BY ngaytao desc",nativeQuery = true)
	List<DonHang> findByTrangThai(Optional<Integer> tt);
	
	@Query(value="SELECT * FROM donhang WHERE tentaikhoan=?1 and trangthai=?2 ORDER BY ngaytao desc",nativeQuery = true)
	List<DonHang> findByIdKHvsTT(String tentaikhoan, Optional<Integer> tt);

	@Query(value="SELECT * FROM DONHANG ORDER BY ngaytao desc",nativeQuery = true)
	Page<DonHang> findAll(Pageable pageable);
	
	/*Summary*/
	@Query(value = "Select count(*) from DONHANG where ngaytao = CAST( GETDATE() AS Date)",nativeQuery= true)
	Long getTodayOrder();

	@Query(value = "Select t.last7Days as 'date', ISNULL(sum(gia*soluong),0) as ' totalPayment' "
			+ "From (Select cast(Getdate()as Date) last7Days  "
			+ "	Union all "
			+ "	Select DateAdd(day,-1,cast(getdate()as date)) "
			+ "	Union all "
			+ "	Select DateAdd(day,-2,cast(getdate()as date)) "
			+ "	Union all "
			+ "	Select DateAdd(day,-3,cast(getdate()as date)) "
			+ "	Union all "
			+ "	Select DateAdd(day,-4,cast(getdate()as date)) "
			+ "	Union all "
			+ "	Select DateAdd(day,-5,cast(getdate()as date)) "
			+ "	Union all "
			+ "	Select DateAdd(day,-6,cast(getdate()as date)) "
			+ "	Union all "
			+ "	Select DateAdd(day,-7,cast(getdate()as date)) "
			+ ") t Left Join DONHANG t1 on cast(t.last7Days as date) = Cast(t1.ngaytao as date) "
			+ "left join CHITIETDONHANG dt on  t1.id = dt.iddonhang "
			+ "Group by cast(t.last7Days as Date)", nativeQuery = true)
	List<Object[]> getRevenueLast7Days();
}
