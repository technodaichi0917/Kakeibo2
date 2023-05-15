package com.example.kakeibo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
        web.ignoring().antMatchers("/css/**", "/html/**");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			//pトップページの制限なし
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
		.and()
			//pトップページ以後はログインページへ遷移するようにする
			.formLogin()	
			.loginPage("/loginForm").permitAll()
			.defaultSuccessUrl("/index")
		.and()
			//pログアウトのpostが飛んで来たらトップページへ遷移する
			.logout()
			.logoutSuccessUrl("/")
		;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("sample1")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
