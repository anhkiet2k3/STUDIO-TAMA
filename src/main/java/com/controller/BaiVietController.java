package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.BaiVietDAO;
import com.dao.BinhLuanBaiVietDAO;
import com.dao.TaiKhoanDAO;
import com.entity.BaiViet;
import com.entity.BinhLuanBaiViet;
import com.entity.DichVu;
import com.entity.TaiKhoan;
import com.service.BaiVietService;
import com.service.DichVuService;

@Controller
public class BaiVietController {

	@Autowired
	BaiVietService baidangService;
	@Autowired
	DichVuService dichvuService;
	@Autowired
	TaiKhoanDAO khdao;
	@Autowired
	BaiVietDAO bvdao;
	@Autowired
	BinhLuanBaiVietDAO bldao;

	@RequestMapping("/baiviet/list")
	public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "ngaydang") String sortBy, Model model) {
		Page<BaiViet> list = baidangService.findAll(page, size, sortBy);
		model.addAttribute("items", list);

		List<BaiViet> list5 = baidangService.findTop5LatestBaidang();
		model.addAttribute("item", list5);

		List<BaiViet> list2 = baidangService.findTop2LatestBaidang();
		model.addAttribute("item2", list2);

		BaiViet list1 = baidangService.findTop1LatestBaidang();
		model.addAttribute("item1", list1);
		return "baiviet/list";
	}

	@RequestMapping("/baiviet/ctbaiviet/{id}")
	public String ctbaiviet(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			Model model, @PathVariable("id") Integer id) {
		BaiViet item = baidangService.findById(id);
		model.addAttribute("item", item);
		Page<DichVu> list = dichvuService.findAll5(page, size);
		model.addAttribute("items", list);
		List<BinhLuanBaiViet> bl = bldao.findByIdbl(id);
		model.addAttribute("bl", bl);
		return "baiviet/ctbaiviet";
	}

	@PostMapping("/baiviet/binhluan/{id}")
	public String create(@PathVariable("id") Integer id, BinhLuanBaiViet cmt, HttpServletRequest request,
			HttpSession session) {
		String tentaikhoan = request.getRemoteUser();
		TaiKhoan test = cmt.getTaikhoan();
		if (tentaikhoan == null) {
			session.setAttribute("message", "Bạn phải đăng nhập để bình luận");

		} else {
			test = khdao.findByTentaikhoan(tentaikhoan);
			BaiViet bv = bvdao.findById(id).get();
			cmt.setTaikhoan(test);
			cmt.setBaiviet(bv);
			bldao.save(cmt);
		}

		return "redirect:/baiviet/ctbaiviet/{id}";
	}

}
