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
@Table(name = "baiviet")
public class BaiViet implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String tieude;
	String noidung;
	String hinhanh;

	@Temporal(TemporalType.DATE)
	@Column(name = "ngaydang")
	Date ngaydang = new Date();

	@JsonIgnore
	@OneToMany(mappedBy = "baiviet")
	List<BinhLuanBaiViet> binhluanbaiviets;
}
