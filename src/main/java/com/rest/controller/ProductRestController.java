package com.rest.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.TaiKhoan;
import com.entity.LePhuc;
import com.service.LePhucService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	LePhucService productService;

	@GetMapping("{id}")
	public LePhuc getOne(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}

	@GetMapping()
	public List<LePhuc> getAll() {
		return productService.findAll();
	}

	@PostMapping
	public LePhuc create(@RequestBody LePhuc product, HttpServletRequest request) {
		return productService.create(product);
	}

	@PutMapping("{id}")
	public LePhuc update(@PathVariable("id") Integer id, @RequestBody LePhuc product) {
		return productService.update(product);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		productService.delete(id);
	}
}
