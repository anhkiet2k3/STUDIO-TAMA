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
@Table(name = "giohang")
public class GioHang implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Integer soluong;

	@ManyToOne
	@JoinColumn(name = "tentaikhoan")
	TaiKhoan taikhoan;

	@ManyToOne
	@JoinColumn(name = "idlephuc")
	LePhuc lephuc;

//	@ManyToOne
//	@JoinColumn(name = "iddichvu")
//	DichVu dichvu;

}
