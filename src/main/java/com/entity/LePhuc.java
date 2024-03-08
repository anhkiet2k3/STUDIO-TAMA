package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="lephuc")
public class LePhuc implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String ten;
	String mota;
	String noidung;
	Integer gia;
	String hinh;
	
	@ManyToOne
	@JoinColumn(name = "idloai")
	Loai loai;

	@JsonIgnore
	@OneToMany(mappedBy = "lephuc")
	List<ChiTietDonHang> chitietmuas;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "lephuc")
	List<BinhLuanLePhuc> binhluanlephucs;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "lephuc")
	List<GioHang> giohangs;
}
