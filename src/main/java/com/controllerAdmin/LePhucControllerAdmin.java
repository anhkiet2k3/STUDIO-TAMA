package com.controllerAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LePhucControllerAdmin {

	@RequestMapping("/admin/lephuc/list")
	public String listlephuc() {
		return "admin/lephuc/listlephuc";
	}

	@RequestMapping("/admin/lephuc/edit")
	public String editlephuc() {
		return "admin/lephuc/editlephuc";
	}
}