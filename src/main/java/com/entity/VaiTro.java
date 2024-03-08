package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "vaitro")
public class VaiTro implements Serializable {
	@Id
	private String id;
	private String ten;

	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<Quyen> quyens;
}