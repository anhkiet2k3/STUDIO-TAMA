package com.config;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.entity.TaiKhoan;
import com.service.TaiKhoanService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Lazy
	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	HttpSession session;

	@Autowired
	TaiKhoanService taiKhoanService;

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return daoAuthenticationProvider;
	}

//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(getDaoAuthenticationProvider());
//	}
	// Cung cấp nguồn dữ liệu đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				TaiKhoan user = taiKhoanService.findByTenTaikhoan(username);
				String password = user.getMatkhau();
				String[] roles = user.getQuyens().stream().map(er -> er.getRole().getId()).collect(Collectors.toList())
						.toArray(new String[0]);
				Map<String, Object> authentication = new HashMap<>();
				authentication.put("user", user);
				byte[] token = (username + ":" + user.getMatkhau()).getBytes();
				authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
				session.setAttribute("authentication", authentication);

				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + " not found!");
			}
		});
	}

	// Phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests(requests -> requests.antMatchers("/giohang/**","/donhang/**").authenticated().antMatchers("/admin/**")
				.hasAnyRole("ADMIN").antMatchers("/taikhoan/doimatkhau", "/taikhoan/chinhsuathongtin")
				.hasAnyRole("USER", "ADMIN").antMatchers("/rest/authorities").hasRole("ADMIN").anyRequest()
				.permitAll());

		http.formLogin(login -> login.loginPage("/taikhoan/login/from").loginProcessingUrl("/taikhoan/login")
				.defaultSuccessUrl("/taikhoan/login/success", false).failureUrl("/taikhoan/login/error")
				.usernameParameter("tentaikhoan").passwordParameter("matkhau"));

		http.rememberMe(me -> me.tokenValiditySeconds(86400));

		http.exceptionHandling(handling -> handling.accessDeniedPage("/taikhoan/unauthoried"));

		http.logout(logout -> logout.logoutUrl("/taikhoan/logoff").logoutSuccessUrl("/taikhoan/logoff/success"));
	}

	// Cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Cho phép truy xuất REST API từ domain khác
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
