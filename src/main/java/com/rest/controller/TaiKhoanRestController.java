package com.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.LePhuc;
import com.entity.TaiKhoan;
import com.service.TaiKhoanService;



@CrossOrigin("*")
@RestController
@RequestMapping("rest")
public class TaiKhoanRestController {
	@Autowired
	private TaiKhoanService accountService;

	@GetMapping("accounts")
	public List<TaiKhoan> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}
	@GetMapping("{tentaikhoan}")
	public TaiKhoan getOne(@PathVariable("tentaikhoan") String  tentaikhoan) {
		return accountService.findByTenTaikhoan(tentaikhoan);
	}

	@PostMapping("accountsManage")
	public TaiKhoan create(@RequestBody TaiKhoan account) {
		return accountService.create(account);
	}

	@PutMapping("accounts/{tentaikhoan}")
	public TaiKhoan update(@RequestBody TaiKhoan account, @PathVariable("tentaikhoan") String tentaikhoan) {
		return accountService.update(account);
	}
	
	@DeleteMapping("accounts/{tentaikhoan}")
	public void delete(@PathVariable("tentaikhoan") String tentaikhoan) {
		 accountService.deletebyId(tentaikhoan);
	}
}
