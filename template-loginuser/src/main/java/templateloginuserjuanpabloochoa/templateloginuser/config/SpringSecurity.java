package templateloginuserjuanpabloochoa.templateloginuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import templateloginuserjuanpabloochoa.templateloginuser.entity.User;
import templateloginuserjuanpabloochoa.templateloginuser.jwt.JwtAuthenticationFilter;
import templateloginuserjuanpabloochoa.templateloginuser.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurity implements UserDetailsService {
	@Autowired
	public UserRepository userRepository;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private AuthenticationProvider authProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						authRequest -> authRequest.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
				.sessionManagement(
						sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();

	}

//	
//	 	 @Bean
//	     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	         http.csrf(csrf -> 
//             csrf
//             .disable())
//	                 .authorizeHttpRequests((authorize) ->
//	                         authorize.requestMatchers("login-users").permitAll()
//	                                 .requestMatchers("/register/**").permitAll()
//	                                 .requestMatchers("/users").hasRole("ADMIN")
//	                 ).formLogin(
//	                         form -> form
//	                                 .loginPage("/login")
//	                                 .loginProcessingUrl("/login")
//	                                 .defaultSuccessUrl("/users")
//	                                 .permitAll()
//	                 ).logout(
//	                         logout -> logout
//	                                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	                                 .permitAll()
//	                 );
//	         return http.build();
//	     }

	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		return user;
	}

}
