package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.VaiTro;


@Service
public interface VaiTroService {
	List<VaiTro> findAll();
}
