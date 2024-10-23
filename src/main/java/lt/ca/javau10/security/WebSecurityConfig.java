package lt.ca.javau10.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import lt.ca.javau10.security.jwt.AuthTokenFilter;


@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	 @Autowired
	 UserDetailsService userDetailsService;
	
	 @Autowired
	 AuthEntryPointJwt unauthorizedHandler;
	 
	 @Bean
	 AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
	 }
	 
	 @Bean
	 DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	 
	 @Bean
	 AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }
	 
	 @Bean
	 PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	 
	 @Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .cors(cors -> cors.configurationSource(request -> {
	                CorsConfiguration configuration = new CorsConfiguration();
	                configuration.setAllowedOrigins(List.of("http://localhost:3000"));  // Allow the frontend origin
	                configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Allow these HTTP methods
	                configuration.setAllowCredentials(true);  // Allow credentials like cookies, authorization headers
	                configuration.setAllowedHeaders(List.of("*"));  // Allow all headers
	                return configuration;
	            }))
	            .csrf(csrf -> csrf.disable())  
	            .authorizeHttpRequests(auth -> 
	             auth.requestMatchers("/api/auth/**").permitAll()
	             	.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
	                 .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
	                 .requestMatchers("/api/users/**").authenticated()//hasAuthority("ROLE_USER")
	                 .anyRequest().authenticated()
	         )
	         .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
	         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	     http.authenticationProvider(authenticationProvider());
	     http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	     return http.build();
	    }
//	 @Bean
//	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	     http.csrf(csrf -> csrf.disable())
//	         .authorizeHttpRequests(auth -> 
//	             auth.requestMatchers("/api/auth/**").permitAll()
//	                 .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
//	                 .requestMatchers("/api/users/**").authenticated()//hasAuthority("ROLE_USER")
//	                 .anyRequest().authenticated()
//	         )
//	         .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//	         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//	     http.authenticationProvider(authenticationProvider());
//	     http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//	     return http.build();
//	 }
	 
}