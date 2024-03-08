package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Loai;

public interface LoaiDAO extends JpaRepository<Loai, Integer>{
    
}
