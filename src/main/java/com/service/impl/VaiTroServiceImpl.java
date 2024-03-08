package com.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VaiTroDAO;
import com.entity.VaiTro;
import com.service.VaiTroService;

@Service
public class VaiTroServiceImpl implements VaiTroService {

	@Autowired
	private VaiTroDAO dao;

	@Override
	public List<VaiTro> findAll() {
		return dao.findAll();
	}

}
