package com.controllerAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.DatLich;
import com.entity.DatLich2;
import com.entity.DichVu;
import com.entity.DonHang;
import com.service.DatLich2Service;
import com.service.DatLichService;
import com.service.DichVuService;

@Controller
public class DichVuControllerAdmin {

	@Autowired
	DichVuService dichvuService;
	
	@Autowired
	DatLichService datlichService;
	
	@Autowired
	DatLich2Service datlich2Service;

	@RequestMapping("/admin/dichvu/list")
	public String listdichvu(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "6") int size,
			Model model) {
		Page<DichVu> list = dichvuService.findAll(page, size);
		model.addAttribute("items", list);
		return "admin/dichvu/listdichvu";
	}

	@GetMapping("/admin/dichvu/add")
	public String addForm(Model model) {
		model.addAttribute("dichVu", new DichVu());
		return "admin/dichvu/editdichvu";
	}

	@PostMapping("/admin/dichvu/add")
	public String addDichVu(@ModelAttribute DichVu dichVu, @RequestParam("hinhanh1File") MultipartFile hinhanh1File,
			@RequestParam("hinhanh2File") MultipartFile hinhanh2File) {
		DichVu savedDichVu = dichvuService.save(dichVu);

		saveImage(hinhanh1File, "hinhanh1", savedDichVu.getId());
		saveImage(hinhanh2File, "hinhanh2", savedDichVu.getId());
		
		return "redirect:/admin/dichvu/list";
	}

	@GetMapping("/admin/dichvu/edit/{id}")
	public String editForm(@PathVariable Integer id, Model model) {
		DichVu dichVu = dichvuService.findById(id);
		model.addAttribute("dichVu", dichVu);
		return "admin/dichvu/editdichvu";
	}

	@PostMapping("/admin/dichvu/edit")
	public String editDichVu(Model model, @ModelAttribute DichVu dichVu, @RequestParam("hinhanh1File") MultipartFile hinhanh1File,
			@RequestParam("hinhanh2File") MultipartFile hinhanh2File) {
		String hinhanh1FileName = hinhanh1File.getOriginalFilename();
		String hinhanh2FileName = hinhanh2File.getOriginalFilename();
		dichVu.setHinhanh1(hinhanh1FileName);
		dichVu.setHinhanh2(hinhanh2FileName);

		DichVu savedDichVu = dichvuService.save(dichVu);

		saveImage(hinhanh1File, "hinhanh1", savedDichVu.getId());
		saveImage(hinhanh2File, "hinhanh2", savedDichVu.getId());
		model.addAttribute("hinhanhc", hinhanh1FileName);
		model.addAttribute("hinhanhp", hinhanh1FileName);
		
		return "redirect:/admin/dichvu/list";
	}

	@GetMapping("/admin/dichvu/delete/{id}")
	public String deleteDichVu(@PathVariable Integer id) {
		dichvuService.delete(id);
		return "redirect:/admin/dichvu/list";
	}

	private void saveImage(MultipartFile file, String prefix, Integer dichVuId) {
		if (file != null && !file.isEmpty()) {
			try {
				String fileName = file.getOriginalFilename();
				File targetFile = new File("src/main/resources/static/assets/images/" + fileName);
				FileCopyUtils.copy(file.getBytes(), targetFile);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
	}
	///////Đặt Lịch////////////////////////////
	
	@RequestMapping("/admin/donhang/datlich")
	public String listdatlist(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size,
			Model model, @RequestParam("trangthai") Optional<Integer> tt) {
		if(tt.isPresent()) {
			List<DatLich2> list1 = datlich2Service.findByTrangThai(tt);
			model.addAttribute("items1", list1);
		}else {
			Page<DatLich2> list = datlich2Service.findAll(page, size);		
			model.addAttribute("items1", list);
		}
		return "admin/donhang/listdatlich";
	}
	
	@RequestMapping("/admin/datlich/timkiem")
	public String tim(Model model, @RequestParam("txtkiem") String sdt) {
		try {
			List<DatLich2> list2 = datlich2Service.findBySDT(sdt);
			model.addAttribute("items1", list2);
		} catch (Exception e) {
			System.out.println("không tìm thấy");
			model.addAttribute("message", "Không tìm thấy lịch đặt của: " + sdt);
		}
		return "admin/donhang/listdatlich";
	}
	
	
	@GetMapping("/admin/datlich/delete/{id}")
	public String deleteDatlich(@PathVariable Integer id) {
		datlich2Service.delete(id);
		return "redirect:/admin/donhang/datlich";
	}
	
	
	@RequestMapping("/admin/datlich/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("datlich", datlichService.findById(id));
		DatLich2 datlich2 = datlich2Service.findById(id);
		model.addAttribute("datlich2", datlich2);
		return "admin/donhang/detaildatlich";
	}
	
	@RequestMapping("/admin/datlich/update")
	public String update(Model model, @ModelAttribute("datlich")  DatLich datlich) {
		datlichService.update(datlich);
		return "redirect:/admin/donhang/datlich";
	}
	
	
	@ModelAttribute("trangthais1")
	public Map<Integer, String>getTrangthais1(){
		Map<Integer, String> map=new HashMap<>();
		map.put(1, "Chờ xác nhận");
		map.put(2, "Đã xác nhận");
		map.put(3, "Đã hủy");
		map.put(4, "Đã hoàn thành");
		return map;
	}
}