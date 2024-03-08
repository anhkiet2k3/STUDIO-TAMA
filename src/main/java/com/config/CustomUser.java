package com.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.entity.TaiKhoan;

public class CustomUser implements UserDetails {

	private TaiKhoan user;

	public CustomUser(TaiKhoan user) {
		super();
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getMatkhau();
	}

	@Override
	public String getUsername() {
		return user.getTentaikhoan();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public void setTen(String ten) {
		this.user.setTen(ten);
	}

	public void setEmail(String email) {
		this.user.setEmail(email);
	}

	public void setSdt(String sdt) {
		this.user.setSdt(sdt);
	}
}
