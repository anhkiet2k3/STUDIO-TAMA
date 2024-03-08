package com.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.BinhLuanLePhucDAO;
import com.dao.DatLichDAO;
import com.dao.DichVuDAO;
import com.dao.SanPhamDAO;
import com.dao.TaiKhoanDAO;
import com.entity.BinhLuanLePhuc;
import com.entity.DatLich;
import com.entity.DichVu;
import com.entity.DonHang;
import com.entity.LePhuc;
import com.entity.TaiKhoan;
import com.service.DatLichService;
import com.service.DichVuService;
import com.service.TaiKhoanService;

@Controller
public class DichVuController {

	@Autowired
	DichVuService dichvuService;

	@Autowired
	TaiKhoanService khachHangService;

	@Autowired
	DatLichService datLichService;

	@Autowired
	TaiKhoanDAO khdao;

	@Autowired
	DichVuDAO dvdao;

	@Autowired
	DatLichDAO dldao;

	@RequestMapping("/dichvu/list")
	public String dichvu(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "6") int size,
			Model model) {
		Page<DichVu> list = dichvuService.findAll(page, size);
		model.addAttribute("items", list);
		return "dichvu/list";
	}

	@RequestMapping("/dichvu/ctdichvu/{id}")
	public String ctdichvu(Model model, @PathVariable("id") Integer id, HttpServletRequest request, HttpSession session) {
		String username = request.getRemoteUser();
		String tentaikhoan = username;
		if (username == null) {
			DichVu item = dichvuService.findByIdFormat(id);
			model.addAttribute("item", item);
			TaiKhoan taikhoan = new TaiKhoan();
			taikhoan.setTen("");
			taikhoan.setSdt("");
			model.addAttribute("user", taikhoan);
			session.setAttribute("message", "Bạn phải đăng nhập để đặt lịch");
		} else {
			DichVu item = dichvuService.findByIdFormat(id);
			model.addAttribute("item", item);
			model.addAttribute("user", khachHangService.findByTenTaikhoan(tentaikhoan));
		}
		
		return "dichvu/ctdichvu";
	}

	@PostMapping("/dichvu/datlich/{id}")
	public String create(Model model, @PathVariable("id") Integer id, DatLich datLich, HttpServletRequest request,
			HttpSession session) {
		String tentaikhoan = request.getRemoteUser();
		TaiKhoan test = datLich.getTaikhoan();
		if (tentaikhoan == null) {
			session.setAttribute("message", "Bạn phải đăng nhập để đặt lịch");

		} else {
			test = khdao.findByTentaikhoan(tentaikhoan);
			DichVu dv = dvdao.findById(id).get();
			datLich.setTaikhoan(test);
			datLich.setDichvu(dv);
			datLich.setTrangthai(1);
			
			// Kiểm tra số lượng lịch đã đặt cho dịch vụ và thời điểm cụ thể
			if (checkBookingLimitForServiceAndTime(dv, datLich.getNgaythue(), datLich.getThoigian())) {
					dldao.save(datLich);
					session.setAttribute("successMessage", "Đặt lịch thành công!");
			} else {
				session.setAttribute("errorMessage",
						"Số lượng lịch đã đạt giới hạn cho dịch vụ và thời điểm này, vui lòng chọn thời gian khác.");
			}


		}
		return "redirect:/dichvu/ctdichvu/{id}";
	}

	public boolean checkBookingLimitForServiceAndTime(DichVu dichvu, String ngaythue, String thoigian) {

		long bookingsCountForServiceAndTime = dldao.countByDichvuAndNgaythueAndThoigian(dichvu, ngaythue, thoigian);

		int bookingLimitForServiceAndTime = 3;

		return bookingsCountForServiceAndTime < bookingLimitForServiceAndTime;
	}
	
	@GetMapping("/dichvu/kiểmtrathoigian/{ngaythue}/{thoigian}")
	@ResponseBody
	public String kiemTraThoiGian(@PathVariable("ngaythue") String ngaythue, @PathVariable("thoigian") String thoigian) {
	    boolean coTheDatLich = datLichService.kiemTraThoiGian(ngaythue, thoigian);
	    return coTheDatLich ? "true" : "false";
	}
	

}
