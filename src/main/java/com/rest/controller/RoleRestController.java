package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.VaiTro;
import com.service.VaiTroService;

@CrossOrigin("*")
@RestController
@RequestMapping("rest/roles")
public class RoleRestController {
	@Autowired
	private VaiTroService roleService;

	@GetMapping
	public List<VaiTro> getAll() {
		return roleService.findAll();
	}
}
