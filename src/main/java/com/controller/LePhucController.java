package com.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.LePhuc;
import com.service.LePhucService;
import com.service.SessionService;
import com.dao.SanPhamDAO;
import com.entity.BinhLuanLePhuc;
import com.entity.ChiTietDonHang;
import com.entity.Loai;
import com.entity.TaiKhoan;

import com.dao.BinhLuanLePhucDAO;
import com.dao.ChiTietDonHangDAO;
import com.dao.TaiKhoanDAO;
import com.dao.LoaiDAO;

@Controller
public class LePhucController {
	@Autowired
	TaiKhoanDAO khdao;
	@Autowired
	BinhLuanLePhucDAO bldao;
	@Autowired
	SanPhamDAO spdao;
	@Autowired
	LePhucService sanphamservice;
	@Autowired
	LoaiDAO ldao;
	@Autowired
	ChiTietDonHangDAO ctdao;
	@Autowired
	SessionService sessionService;
	@RequestMapping("/lephuc/list")
	public String lephuc(Model model, @RequestParam("cid") Integer cid) {
		if (cid != 0) {
			List<LePhuc> sps = spdao.findByIdsp(cid);
			model.addAttribute("sps", sps);
		} else {
			List<LePhuc> sps = spdao.findAll();
			model.addAttribute("sps", sps);
		}
		List<Loai> l = ldao.findAll();
		model.addAttribute("l", l);
		return "lephuc/list";
	}

	@RequestMapping("/lephuc/ctlephuc/{id}")
	public String ctlephuc(Model model, @PathVariable("id") Integer id) {
		LePhuc sp = sanphamservice.findById(id);
		model.addAttribute("sp", sp);
		List<BinhLuanLePhuc> bl = bldao.findByIdbl(id);
		model.addAttribute("bl", bl);
		Loai clt = sp.getLoai();
		List<LePhuc> cl = spdao.findByIdsp(clt.getId());
		model.addAttribute("cl", cl);
		List<LePhuc> ct = spdao.findBytop();
		model.addAttribute("ct",ct);
		return "lephuc/ctlephuc";
	}

	@PostMapping("/lephuc/binhluan/{id}")
	public String create(@PathVariable("id") Integer id, BinhLuanLePhuc cmt, HttpServletRequest request,
			HttpSession session) {
		String tentaikhoan = request.getRemoteUser();
		TaiKhoan test = cmt.getTaikhoan();
		if (tentaikhoan == null) {
			session.setAttribute("message", "Bạn phải đăng nhập để bình luận");

		} else {
			test = khdao.findByTentaikhoan(tentaikhoan);
			LePhuc sp = spdao.findById(id).get();
			cmt.setTaikhoan(test);
			cmt.setLephuc(sp);
			bldao.save(cmt);
		}

		return "redirect:/lephuc/ctlephuc/{id}";
	}
	@RequestMapping("/lephuc/gia")
	public String searchGia(Model model, 
			@RequestParam(value =  "min", required=false) Optional<Integer> min
			,@RequestParam(value = "max", required=false) Optional<Integer> max
			){
		Integer minGia = min.orElse(Integer.MIN_VALUE);
		Integer maxGia = max.orElse(Integer.MAX_VALUE);
		sessionService.set("max", maxGia);
		sessionService.set("min", minGia);
		List<LePhuc> sps = spdao.findByGia(minGia, maxGia);
		List<Loai> l = ldao.findAll();
		model.addAttribute("l", l);
		model.addAttribute("sps", sps);
		return "lephuc/list";
	}
	
	@RequestMapping("/list/timkiem")
	public String tim(Model model, @RequestParam("txtkiem") String ma) {
			String ten="%"+ma+"%";
			List<LePhuc> sps = spdao.findByten(ten);
			model.addAttribute("sps", sps);
			List<Loai> l = ldao.findAll();
			model.addAttribute("l", l);
		return "lephuc/list";
	}

}