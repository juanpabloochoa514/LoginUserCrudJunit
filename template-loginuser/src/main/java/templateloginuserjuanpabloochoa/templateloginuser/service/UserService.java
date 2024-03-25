package templateloginuserjuanpabloochoa.templateloginuser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import templateloginuserjuanpabloochoa.templateloginuser.entity.Role;
import templateloginuserjuanpabloochoa.templateloginuser.entity.User;
import templateloginuserjuanpabloochoa.templateloginuser.jwt.JwtService;
import templateloginuserjuanpabloochoa.templateloginuser.repository.UserRepository;
@Service
public class UserService {
	
		private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
		@Autowired
	    private  UserRepository userRepository;
	    @Autowired
	    private  JwtService jwtService;
	    @Autowired
	    private  AuthenticationManager authenticationManager;
	    @Autowired
	    private  PasswordEncoder passwordEncoder;
	   

	    public String login(LoginRequest request) {
	    	try {
	    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		        String token=jwtService.getToken(user);
		        return token;
	    	}catch(Exception e) {
	    		return "User not found";
	    	} 
	    }


	    public String register(RegisterRequest request) {
	    	try {
	    		User user = new User(request.getName(),request.getLastname(),request.getEmail(),passwordEncoder.encode( request.getPassword()),Role.USER);
	    		userRepository.save(user);
		        String token=jwtService.getToken(user);
		        return token;
	    	}catch(IllegalArgumentException e) {
	    		e.getStackTrace();
	    		log.error("Error report from the register in the class UserService = "+e);
	    		return "We cant register this user for the moment, data base is doesnt work ";
	    	}
	    	
	    }
}
