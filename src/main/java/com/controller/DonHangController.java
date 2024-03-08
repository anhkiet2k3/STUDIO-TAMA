package com.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.DatLich;
import com.entity.DonHang;
import com.service.DatLich2Service;
import com.service.DatLichService;
import com.service.DonHangService;
import com.service.TaiKhoanService;

@Controller
public class DonHangController {
	@Autowired
	DonHangService orderService;
	
	@Autowired
	TaiKhoanService khachHangService;
	
	@Autowired
	DatLichService datLichService;
	
	@Autowired
	DatLich2Service datLich2Service;
	
	@RequestMapping("/donhang/checkout")
	public String checkout(Model model, HttpServletRequest request) {
		String username=request.getRemoteUser();
		String tentaikhoan=username;
		model.addAttribute("user",khachHangService.findByTenTaikhoan(tentaikhoan));
		return "donhang/checkout";
	}
	
	@RequestMapping("/donhang/list")
	public String list(Model model, HttpServletRequest request, @RequestParam("trangthai") Optional<Integer> tt) {
		if (tt.isPresent()) {
			String username=request.getRemoteUser();
			String tentaikhoan=username;
			model.addAttribute("donhang", orderService.findByIdKHvsTT(tentaikhoan, tt));
			model.addAttribute("datlich", datLich2Service.findByIdKhachHang(tentaikhoan));

		} else {
			String username=request.getRemoteUser();
			String tentaikhoan=username;
			model.addAttribute("donhang", orderService.findByIdKhachHang(tentaikhoan));
			model.addAttribute("datlich", datLich2Service.findByIdKhachHang(tentaikhoan));

		}
		return "donhang/list";
	}
	
	@RequestMapping("/donhang/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("donhang", orderService.findById(id));
		return "donhang/detail";
	}
	
	@RequestMapping("/donhang/huy")
	public String update(Model model, @ModelAttribute("donhang") DonHang donHang) {
		donHang.setTrangthai(5);
		orderService.update(donHang);
		return "redirect:/donhang/list";
	}
	
	@RequestMapping("/donhang/huy/{id}")
	public String huydon(@PathVariable("id") Integer idDonHang) {
		DonHang donHang = orderService.findById(idDonHang);
		donHang.setTrangthai(5);
		orderService.update(donHang);
		return "redirect:/donhang/list";
	}
	
	@RequestMapping("/datlich/huy/{id}")
	public String huylich(@PathVariable("id") Integer iddatlich) {
		DatLich datlich = datLichService.findById(iddatlich);
		datlich.setTrangthai(3);
		datLichService.update(datlich);
		return "redirect:/donhang/list";
	}
}
