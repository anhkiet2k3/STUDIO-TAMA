package com.controllerAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ThongKeControllerAdmin {
	
	@RequestMapping("/admin")
	public String listsanpham() {
		return "admin/thongke/sanphambanchay";
	}
	
	@RequestMapping("/admin/nguoimuanhieunhat")
	public String listnguoimua() {
		return "admin/thongke/nguoimuanhieunhat";
	}
}
