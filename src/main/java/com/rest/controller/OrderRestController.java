package com.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.DonHang;
import com.entity.LePhuc;
import com.fasterxml.jackson.databind.JsonNode;
import com.service.DonHangService;
import com.service.LePhucService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	DonHangService orderService;

	@PostMapping()
	public DonHang create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}

	@PutMapping("{id}")
	public DonHang update(@PathVariable("id") Integer id, @RequestBody DonHang donhang) {
		return orderService.update(donhang);
	}

}
