package com.controllerAdmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.BinhLuanBaiVietDAO;
import com.dao.BinhLuanLePhucDAO;
import com.dao.TaiKhoanDAO;
import com.dao.SanPhamDAO;
import com.entity.BinhLuanBaiViet;
import com.entity.BinhLuanLePhuc;
import com.entity.TaiKhoan;
import com.entity.Loai;
import com.entity.LePhuc;
import com.service.BinhLuanLePhucService;

@Controller
public class BinhluanControllerAdmin {
	@Autowired
	SanPhamDAO spdao;
	@Autowired
	TaiKhoanDAO dao;
	@Autowired
	BinhLuanLePhucService bl;
	@Autowired
	BinhLuanBaiVietDAO blbvdao;

	@RequestMapping("/admin/binhluan/lephuc")
	public String editBinhluan(Model model) {
		List<BinhLuanLePhuc> b = bl.findAll();
		model.addAttribute("b", b);
		return "admin/binhluan/binhluanlephuc";
	}

	@RequestMapping("/binhluan/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		bl.delete(id);
		return "redirect:/admin/binhluan/binhluanlephuc";
	}

	@RequestMapping("/admin/binhluan/baiviet")
	public String editBaiviet(Model model) {
		List<BinhLuanBaiViet> b = blbvdao.findAll();
		model.addAttribute("b", b);
		return "admin/binhluan/binhluanbaiviet";
	}

	@RequestMapping("/binhluanbv/delete/{id}")
	public String deleteBaiviet(@PathVariable("id") Integer id) {
		blbvdao.deleteById(id);
		return "redirect:/admin/binhluan/baiviet";
	}
}
