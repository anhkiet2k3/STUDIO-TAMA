package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.VaiTro;

@Repository
public interface VaiTroDAO extends JpaRepository<VaiTro, String>{

}
