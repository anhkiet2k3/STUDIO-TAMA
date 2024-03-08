package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LienHeController {

	@RequestMapping("/lienhe")
	public String baiviet() {
		return "lienhe/thongtin";
	}
}
