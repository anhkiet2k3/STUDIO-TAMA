package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.BaiVietDAO;
import com.dao.SanPhamDAO;
import com.entity.BaiViet;
import com.entity.DichVu;
import com.entity.LePhuc;
import com.service.BaiVietService;
import com.service.DichVuService;
import com.service.LePhucService;


@Controller
public class HomeController {

	@Autowired
	DichVuService dichvuService;
	
	@Autowired
	SanPhamDAO spdao;
	
	@Autowired
	BaiVietService baivietService;

	@RequestMapping("/")
	public String home(Model model) {
		List<LePhuc> lp = spdao.findByIdsptop4();
		List<BaiViet> listbv4 = baivietService.findTop4LatestBaidang();
		model.addAttribute("listbv", listbv4);
		model.addAttribute("lp",lp);
		model.addAttribute("dichvu1", dichvuService.findById(1));
		model.addAttribute("dichvu2", dichvuService.findById(2));
		model.addAttribute("dichvu3", dichvuService.findById(3));
		return "trangchu/index";
	}
}
