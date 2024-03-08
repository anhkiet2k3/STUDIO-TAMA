package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="datlich")
public class DatLich2 implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tentaikhoan")
    private TaiKhoan taikhoan;

    @ManyToOne
    @JoinColumn(name = "iddichvu")
    private DichVu dichvu;
    @Column(name = "ngaythue")
    private Date ngaythue = new Date();
    @Column(name = "thoigian")
    private Date thoigian = new Date();
    private String diachi;
    private String ghichu;
    private boolean cachnhan;
    private Integer trangthai;
    
}
