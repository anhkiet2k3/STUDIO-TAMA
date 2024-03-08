package com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "quyen", uniqueConstraints = { @UniqueConstraint(columnNames = { "tentaikhoan", "vaitroid" }) })
public class Quyen implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "tentaikhoan")
	private TaiKhoan taikhoan;

	@ManyToOne
	@JoinColumn(name = "vaitroid")
	private VaiTro role;
}