package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@SuppressWarnings("serial")
@Data
@Entity
@Table(name="taikhoan")
public class TaiKhoan implements Serializable{
	@Id
	@NotBlank(message="Không để trống tên tài khoản")
	String tentaikhoan;
	@NotBlank(message="Không để trống mật khẩu")
	String matkhau;
	@NotBlank(message="Không để trống họ tên")
	String ten;
	@NotBlank(message="Không để trống số điện thoại")
	String sdt;
	@NotBlank(message="Không để trống email")
	@Email(message = "Email chưa đúng định dạng")
	String email;
	@NotBlank(message="Không để trống")
	String diachi;
	
	
	String hinhanh;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<DonHang> donhangs;

	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<GioHang> giohangs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<BinhLuanLePhuc> binhluanklephucs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<BinhLuanBaiViet> binhluanbaiviets;

	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan", fetch = FetchType.EAGER)
	List<Quyen> quyens;
	
}
