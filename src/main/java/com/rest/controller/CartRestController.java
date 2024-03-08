package com.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.GioHang;
import com.service.impl.GioHangServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cart")
public class CartRestController {

	@Autowired
	GioHangServiceImpl gioHangServiceImpl;

	@PostMapping("/{id}")
	public ResponseEntity<?> addCart(
			@PathVariable("id") Integer idLePhuc) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
			String username = authentication.getName();

			return ResponseEntity.ok(gioHangServiceImpl.addCart(username, idLePhuc));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"errorCode\": \"AUTHEN\"}");
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCart(
			@PathVariable("id") Integer idcart) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
			String username = authentication.getName();
			gioHangServiceImpl.delete(idcart);
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"errorCode\": \"AUTHEN\"}");
		}

	}
	
	@PostMapping("/clear")
	public ResponseEntity<?> clear() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
			String username = authentication.getName();
			gioHangServiceImpl.clear(username);
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"errorCode\": \"AUTHEN\"}");
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(
			@RequestParam("idCart") Integer idCart,
			@RequestParam("soluong") Integer soluong) {
		System.out.println(idCart);
		System.out.println(soluong);
		return ResponseEntity.ok(gioHangServiceImpl.update(idCart, soluong));
	}

	@GetMapping()
	public ResponseEntity<?> getCart() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
			String username = authentication.getName();
			System.out.println(username);
			return ResponseEntity.ok(gioHangServiceImpl.getCartByUser(username));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"errorCode\": \"AUTHEN\"}");
		}

	}

}
