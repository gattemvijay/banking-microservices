package com.mybank.userservice.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.headers(headers -> headers
                .frameOptions(frame -> frame.disable())) // Allow H2 frames
				.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/users/login", "/api/users/register", "/h2-console/**")
				.permitAll().anyRequest()
				.authenticated())
				.formLogin(form -> form.disable());

		return http.build();
	}

	/*
	 * http .csrf().disable() // allow POST from frontend/Postman .cors() // enable
	 * CORS in Spring Security .and() .authorizeHttpRequests()
	 * .requestMatchers("/api/users/login", "/api/users/register").permitAll() //
	 * allow both .anyRequest().authenticated() // everything else requires auth
	 * .and() .formLogin().disable() // DISABLE default Spring form login
	 * .httpBasic(); // optional: can switch to JWT later
	 */

	/*
	 * return http .csrf(ServerHttpSecurity.CsrfSpec::disable)
	 * .authorizeExchange(exchange -> exchange .pathMatchers("/api/users/login",
	 * "/api/users/register").permitAll() .anyExchange().authenticated() ) .build();
	 */

	/*
	 * http.csrf(csrf -> csrf.disable()) .authorizeHttpRequests(auth -> auth
	 * .requestMatchers("/api/users/register").permitAll()
	 * .requestMatchers("/api/users/login").permitAll()
	 * .anyRequest().authenticated());
	 */

	/*
	 * http .csrf(csrf -> csrf.disable()) // disable for API POSTs (you can enable
	 * later with token) .authorizeHttpRequests()
	 * .requestMatchers("/api/users/login").permitAll() // allow login without auth
	 * .anyRequest().authenticated() // all other endpoints need auth .and()
	 * .httpBasic();
	 */

	// return http.build();
	//}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:3000")); // React app
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
