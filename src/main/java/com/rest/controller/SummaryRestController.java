package com.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.ChiTietDonHangDAO;
import com.service.QuyenService;
import com.service.ChiTietDonHangService;
import com.service.DonHangService;
import com.service.TaiKhoanService;
import com.service.LePhucService;

@RestController
@CrossOrigin("*")
@RequestMapping("rest/summary")
public class SummaryRestController {
	@Autowired
	private LePhucService productService;
	@Autowired
	private DonHangService orderService;
	@Autowired
	private ChiTietDonHangService orderDetailService;
	@Autowired
	private TaiKhoanService accountService;
	@Autowired
	private QuyenService authService;

	@Autowired
	private ChiTietDonHangDAO daodt;

//	@Autowired private XDate xdate;
	/* -- First Row Content -- */
	@GetMapping("firstRow")
	public Map<String, Object> showSummary() {
		Map<String, Object> map = new HashMap<>();
		map.put("todayOrder", orderService.getToDayOrder());
		map.put("totalOrder", orderService.totalOrder());
		map.put("totalProduct", productService.getTotalProduct());
		map.put("todayIncome", orderDetailService.getTodayIncome());
		map.put("totalIncome", orderDetailService.getTotalIncome());
		map.put("totalCustomer", authService.getTotalCustomer());
		map.put("totalAccount", accountService.getTotalAccount());
		return map;
	}

	@GetMapping("secondRow")
	public Map<String, List<Object[]>> showSecond() {
		Map<String, List<Object[]>> map = new HashMap<>();
		map.put("revenueLast7Days", orderService.getRevenueLast7Days());
		map.put("productSoldByCate", productService.numberOfProductSoldByType());
		return map;
	}

	@GetMapping("thirdRow")
	public Map<String, List<Object[]>> showThird() {
		Map<String, List<Object[]>> map = new HashMap<>();
		map.put("percentSoldByCate", productService.getPercentByCate());

		return map;
	}

	@GetMapping("fourthRow")
	public Map<String, List<Object[]>> fourthRow() {
		Map<String, List<Object[]>> map = new HashMap<>();
		map.put("top10SoldProduct", productService.top10Product());
		return map;
	}

	@GetMapping("fifthRow")
	public Map<String, List<Object[]>> fifthRow() {
		Map<String, List<Object[]>> map = new HashMap<>();
		map.put("topCustomer", accountService.top10Customer());
		return map;
	}
}
