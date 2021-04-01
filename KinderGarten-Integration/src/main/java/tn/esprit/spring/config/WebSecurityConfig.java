package tn.esprit.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	private DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		//.antMatchers("/kindergarten/servlet/admin/addparent").permitAll()
	/*	.antMatchers("/kindergarten/servlet/child/").authenticated()
		.antMatchers("/kindergarten/servlet/child/").authenticated()
		.antMatchers("/kindergarten/servlet/classroom/").authenticated()
		.antMatchers("/kindergarten/servlet/complaint/").authenticated()
		.antMatchers("/kindergarten/servlet/complaint/post/").authenticated()
		.antMatchers("/kindergarten/servlet/event/donnation/").authenticated()
		.antMatchers("/kindergarten/servlet/event/").authenticated()
		.antMatchers("/kindergarten/servlet/kindergarten/").authenticated()
		.antMatchers("/kindergarten/servlet/parent/").authenticated()
		.antMatchers("/kindergarten/servlet/report/").authenticated()*/
		.anyRequest().permitAll()
		.and()
		
		.httpBasic()
		.and().cors();
	
		

	}
	
}
