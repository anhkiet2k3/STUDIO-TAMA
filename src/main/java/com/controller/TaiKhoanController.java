package com.controller;

import java.security.Principal;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.CustomUser;
import com.dao.QuyenDAO;
//import com.config.CustomUser;
import com.dao.TaiKhoanDAO;
import com.entity.Quyen;
import com.entity.VaiTro;
import com.entity.TaiKhoan;
import com.service.TaiKhoanService;
import com.service.ParamService;
import com.service.SessionService;
import com.service.impl.MailerServiceImpl;

@Controller
public class TaiKhoanController {
	@Autowired
	private BCryptPasswordEncoder passEncode;

	Random random = new Random(1000);

	@Autowired
	TaiKhoanDAO accountDAO;
	@Autowired
	QuyenDAO aDao;
	@Autowired
	SessionService session;
	@Autowired
	ParamService paramService;
	@Autowired
	ServletContext app;
	@Autowired
	TaiKhoanService khService;

	@RequestMapping("/taikhoan/login/from")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng nhập thông tin");
		return "taikhoan/login";
	}

	@RequestMapping("/taikhoan/login/success")
	public String login(Model model, TaiKhoan user) {
		model.addAttribute("message", "Bạn đã đăng nhập thành công!");
		return "taikhoan/login";
	}

	@RequestMapping("/taikhoan/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "taikhoan/login";
	}

	@RequestMapping("/taikhoan/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "taikhoan/login";
	}

	@RequestMapping("/taikhoan/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất!");
		return "taikhoan/login";
	}

	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/taikhoan/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}

	@RequestMapping("/taikhoan/register")
	public String register(Model model) {
		TaiKhoan item = new TaiKhoan();
		model.addAttribute("items", item);
		model.addAttribute("message", "Vui lòng điền các thông tin");
		return "taikhoan/register";
	}
	
	@PostMapping("/register/create")
	public String create(Model model, @Validated @ModelAttribute("items")  TaiKhoan item, Errors errors) {
		if(errors.hasErrors()) {
			model.addAttribute("mess", "Vui lòng điền đầy đủ");
			return "taikhoan/register";
		}else {
			item.setHinhanh("logo_tama.webp");
			TaiKhoan user = khService.create(item);
			
			VaiTro role = new VaiTro();
			role.setId("USER");
			
			//thêm vai trò cho cho tài khoản mới tạo !Ok
			Quyen authority = new Quyen();
			authority.setRole(role);
			authority.setTaikhoan(user);
			aDao.save(authority);
			model.addAttribute("message" , "Đăng kí thành công , vui lòng đăng nhập");
			return "taikhoan/login" ;
		}	

	}

	@RequestMapping("/taikhoan/doimatkhau")
	public String doimatkhau(Model model) {
		model.addAttribute("taikhoan", "");
		return "taikhoan/doimatkhau";
	}

	@PostMapping("/taikhoan/doimatkhau")
	public String update(Principal p, @RequestParam("matkhau") String matkhau,
			@RequestParam("matkhaumoi") String matkhaumoi, @RequestParam("confimmaukhaumoi") String confimmaukhaumoi,
			Model model) {
		String tentaikhoan = p.getName();

		TaiKhoan user = accountDAO.findByTentaikhoan(tentaikhoan);

		boolean f = passEncode.matches(matkhau, user.getMatkhau());
		if (f) {
			if (matkhaumoi.equals(confimmaukhaumoi)) {
				user.setMatkhau(passEncode.encode(matkhaumoi));
				accountDAO.save(user);
				model.addAttribute("message", "Đổi mật khẩu thành công");
				return "taikhoan/doimatkhau";
			} else {
				model.addAttribute("message", "Mật khẩu mới không trùng khớp");
				return "taikhoan/doimatkhau";
			}
		} else {
			model.addAttribute("message", "Mật khẩu cũ sai");

		}

		return "redirect:/taikhoan/doimatkhau";
	}

	@Autowired
	MailerServiceImpl mailer;

	@RequestMapping("/taikhoan/quenmatkhau")
	public String quenmatkhau() {
		return "taikhoan/quenmatkhau";
	}

	@PostMapping("quenmatkhau")
	public String change(Model model, HttpSession session) {
		String email = paramService.getString("email", "");
		String tentaikhoan = paramService.getString("tentaikhoan", "");
		String subject = "Mã OTP để xác nhận thông tin đổi mật khẩu";
		String body = "Mã OTP của bạn là: ";
		int otp = random.nextInt(99999999);
		try {
			TaiKhoan user = accountDAO.findByTentaikhoan(tentaikhoan);
			if (!user.getEmail().equals(email)) {
				model.addAttribute("message", "Sai Email!");
			} else {
				session.setAttribute("myOtp", otp);
				session.setAttribute("email", email);
				mailer.send(email, subject, body + otp);
				return "/taikhoan/formOTP";
			}
		} catch (Exception e) {
			model.addAttribute("message", "Tài Khoản Không Tồn Tại!");
		}
		return "taikhoan/quenmatkhau";
	}

	@PostMapping("/OTP")
	public String formOPT(@RequestParam("otp") int otp, HttpSession session) {
		int myOtp = (int) session.getAttribute("myOtp");
		String email = (String) session.getAttribute("email");

		if (myOtp == otp) {
			TaiKhoan user = this.accountDAO.getbyEmail(email);
			if (user == null) {
				session.setAttribute("message", "Tài khoản không tồn tại với email này");
			} else {

			}
			return "taikhoan/doimatkhaumoi";
		} else {
			session.setAttribute("message", "Mã OTP không đúng");
			return "taikhoan/formOTP";
		}
	}

	@PostMapping("/change-password")
	public String changePass(@RequestParam("newpass") String newpass, @RequestParam("confimpass") String confimpass,
			HttpSession session) {
		if (newpass.equals(confimpass)) {
			String email = (String) session.getAttribute("email");
			TaiKhoan user = this.accountDAO.getbyEmail(email);
			user.setMatkhau(this.passEncode.encode(newpass));

			VaiTro role = new VaiTro();
			role.setId("USER");

			// Thêm vai trò cho người dùng
			Quyen authority = new Quyen();
			authority.setRole(role);
			authority.setTaikhoan(user);

			aDao.save(authority);

			accountDAO.save(user);
			session.setAttribute("message", "Mật khẩu đã được cập nhập ! Vui lòng đăng nhập lại ");
			return "redirect:taikhoan/login/from";

		} else {
			session.setAttribute("message", "Mật khẩu xác nhận không đúng");
			return "taikhoan/doimatkhaumoi";
		}
	}

	@GetMapping("/taikhoan/trangcanhan")
	public String profile(@AuthenticationPrincipal CustomUser loger, Model model, HttpServletRequest request) {
		String tentaikhoan = request.getRemoteUser();
		TaiKhoan user = khService.findByTenTaikhoan(tentaikhoan);
		model.addAttribute("user", user);
		return "taikhoan/trangcanhan";
	}

	@GetMapping("/taikhoan/chinhsuathongtin")
	public String updateTT(@AuthenticationPrincipal CustomUser loger, Model model, HttpServletRequest request) {
		String tentaikhoan = request.getRemoteUser();
		TaiKhoan user = khService.findByTenTaikhoan(tentaikhoan);
		model.addAttribute("user", user);
		return "taikhoan/chinhsuathongtin";
	}

	@PostMapping("/chinhsuaTT")
	public String updateUser(@AuthenticationPrincipal CustomUser loger, @RequestParam("tentaikhoan") String tentaikhoan,
			@RequestParam("ten") String ten, @RequestParam("sdt") String sdt, @RequestParam("email") String email,
			@RequestParam("hinhanh") String hinhanh, @RequestParam("diachi") String diachi, HttpSession session) {
		TaiKhoan user = this.accountDAO.findByTentaikhoan(tentaikhoan);
		user.setEmail(email);
		user.setSdt(sdt);
		user.setTen(ten);
		user.setDiachi(diachi);
		user.setHinhanh(hinhanh);
		accountDAO.save(user);
		session.setAttribute("messsage", "Thông tin đã được cập nhập ");
		return "redirect:taikhoan/trangcanhan";

	}

}
