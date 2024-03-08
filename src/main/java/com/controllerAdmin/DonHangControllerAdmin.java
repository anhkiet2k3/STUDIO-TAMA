package com.controllerAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.DonHang;
import com.service.DonHangService;

@Controller
public class DonHangControllerAdmin {

	@Autowired
	DonHangService orderService;
	
	@RequestMapping("/admin/donhang/list")
	public String listlephuc(Model model, @RequestParam("p") Optional<Integer> p, @RequestParam("trangthai") Optional<Integer> tt) {
		if(tt.isPresent()) {
			List<DonHang> page = orderService.findByTrangThai(tt);
			model.addAttribute("page", page);
		}else {
			Pageable pageable = PageRequest.of(p.orElse(0), 5);
			Page<DonHang> page = orderService.findAll(pageable);
			int currentPage =1;
			int totalItems = page.getNumberOfElements();
			int totalPages = page.getTotalPages();
			model.addAttribute("totalItems", totalItems);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("page", page);
		}

		Integer slcxn= orderService.findSl(1);
		Integer ttcxn= orderService.findTt(1);
		model.addAttribute("slcxn", slcxn);
		model.addAttribute("ttcxn", ttcxn);

		Integer sldg= orderService.findSl(3);
		Integer ttdg= orderService.findTt(3);
		model.addAttribute("sldg", sldg);
		model.addAttribute("ttdg", ttdg);
		
		Integer sldag= orderService.findSl(4);
		Integer ttdag= orderService.findTt(4);
		model.addAttribute("sldag", sldag);
		model.addAttribute("ttdag", ttdag);
		
		Integer sldh= orderService.findSl(5);
		Integer ttdh= orderService.findTt(5);
		model.addAttribute("sldh", sldh);
		model.addAttribute("ttdh", ttdh);

		return "admin/donhang/listdonhang";
	}
	
	@RequestMapping("/admin/donhang/timkiem")
	public String tim(Model model, @RequestParam("txtkiem") Integer ma) {

		try {
			model.addAttribute("page", orderService.findById(ma));
		} catch (Exception e) {
			System.out.println("không tìm thấy");
			model.addAttribute("message", "Không tìm thấy đơn hàng có mã: " + ma);
		}
		return "admin/donhang/listdonhang";
	}
	
	@RequestMapping("/admin/donhang/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("donhang", orderService.findById(id));
		return "admin/donhang/detaildonhang";
	}
	
	@RequestMapping("/admin/donhang/update")
	public String update(Model model, @ModelAttribute("donhang")  DonHang donHang) {
				orderService.update(donHang);
		return "redirect:/admin/donhang/list";
	}
	
	@RequestMapping("/admin/donhang/delete/{id}")	
	public String delete(@PathVariable("id") Integer id) {
		orderService.delete(id);
		return "redirect:/admin/donhang/list";
	}
	@ModelAttribute("trangthais")
	public Map<Integer, String>getTrangthais(){
		Map<Integer, String> map=new HashMap<>();
		map.put(1, "Chờ xác nhận");
		map.put(2, "Đang chuẩn bị");
		map.put(3, "Đang giao");
		map.put(4, "Đã giao");
		map.put(5, "Đã hủy");
		return map;
	}
}