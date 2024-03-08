package com.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Quyen;
import com.service.QuyenService;

@CrossOrigin("*")
@RestController
@RequestMapping("rest")
public class AuthorityRestController {
	@Autowired
	private QuyenService authService;

	@GetMapping("authorities")
	public List<Quyen> findAll(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return authService.findAuthoritiesOfAdministrators();
		}
		return authService.findAll();
	}

	@GetMapping("authoritiesOne")
	public List<Quyen> getOneByRole(@RequestParam("tentaikhoan") String tentaikhoan) {
		return authService.getOneByRole(tentaikhoan);
	}

	@PostMapping("authorities")
	public Quyen post(@RequestBody Quyen auth) {
		return authService.create(auth);
	}

	@DeleteMapping("authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authService.delete(id);
	}

	@DeleteMapping("authoritiesOne/{tentaikhoan}")
	public void deleteByUsername(@PathVariable("tentaikhoan") String tentaikhoan) {
		System.out.println("username del: " + tentaikhoan);
		authService.deleteByTaikhoan(tentaikhoan);
	}
}
