package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.TaiKhoanDAO;
import com.entity.TaiKhoan;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	 TaiKhoanDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String tentaikhoan) throws UsernameNotFoundException {
		TaiKhoan user = dao.findByTentaikhoan(tentaikhoan);
		if(user != null) {
			return new CustomUser(user);
		}
		throw new UsernameNotFoundException("User không tồn tại!");
	}
	
	

}
