package templateloginuserjuanpabloochoa.templateloginuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import templateloginuserjuanpabloochoa.templateloginuser.entity.Role;
import templateloginuserjuanpabloochoa.templateloginuser.entity.User;
import templateloginuserjuanpabloochoa.templateloginuser.jwt.AuthResponse;
import templateloginuserjuanpabloochoa.templateloginuser.jwt.JwtService;
import templateloginuserjuanpabloochoa.templateloginuser.jwt.LoginRequest;
import templateloginuserjuanpabloochoa.templateloginuser.jwt.RegisterRequest;
import templateloginuserjuanpabloochoa.templateloginuser.repository.UserRepository;
@Service
public class UserService {
		@Autowired
	    private  UserRepository userRepository;
	    @Autowired
	    private  JwtService jwtService;
	    @Autowired
	    private  AuthenticationManager authenticationManager;
	    @Autowired
	    private  PasswordEncoder passwordEncoder;

	    public AuthResponse login(LoginRequest request) {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
	        User user=userRepository.findByUserName(request.getUserName());
	        String token=jwtService.getToken(user);
	        AuthResponse authresponseToken = new AuthResponse(token);
	        return authresponseToken;
	    }
	    @Secured({"USER"})
	    public AuthResponse register(RegisterRequest request) {
	    	User user = new User(request.getName(),request.getLastname(),request.getEmail(),passwordEncoder.encode( request.getPassword()),Role.USER);
	        userRepository.save(user);
	        String token=jwtService.getToken(user);
	        AuthResponse authresponseToken = new AuthResponse(token);
	        return authresponseToken;
	    }
}
