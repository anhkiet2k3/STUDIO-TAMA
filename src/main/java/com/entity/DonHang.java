package com.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="donhang")
public class DonHang implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Integer tongtien;
	String diachi;
	Integer trangthai;
	String ngaytao;
	Boolean cachnhan;
	Boolean hinhthuc;
	
	@ManyToOne
	@JoinColumn(name = "tentaikhoan")
	TaiKhoan taikhoan;
	
	@JsonIgnore
	@OneToMany(mappedBy = "donhang")
	List<ChiTietDonHang> chitietdonhangs;
	

}
