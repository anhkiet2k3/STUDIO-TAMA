package com.controllerAdmin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.TaiKhoanDAO;
import com.entity.TaiKhoan;
import com.service.CookieService;
import com.service.ParamService;
import com.service.SessionService;



@Controller
public class TaiKhoanControllerAdmin {

	@RequestMapping("/admin/taikhoan/list")
	public String listlephuc() {
		return "admin/taikhoan/listtaikhoan";
	}

	@RequestMapping("/admin/taikhoan/edit")
	public String editlephuc(){
		return "admin/taikhoan/edittaikhoan";
	}
}
