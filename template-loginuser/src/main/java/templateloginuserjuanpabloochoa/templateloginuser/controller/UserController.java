package templateloginuserjuanpabloochoa.templateloginuser.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import templateloginuserjuanpabloochoa.templateloginuser.jwt.AuthResponse;
import templateloginuserjuanpabloochoa.templateloginuser.jwt.LoginRequest;
import templateloginuserjuanpabloochoa.templateloginuser.jwt.RegisterRequest;
import templateloginuserjuanpabloochoa.templateloginuser.service.UserService;
@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserService userService; 
	
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
    	try {

            return new ResponseEntity<AuthResponse>(userService.login(request),HttpStatus.ACCEPTED);
            
    	}catch(Exception e) {
    		
    		return  new ResponseEntity<AuthResponse>(HttpStatus.NOT_FOUND);
    	}
    	
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest)
    {
    	try {
    		
    		return new ResponseEntity<AuthResponse>(userService.register(registerRequest),HttpStatus.ACCEPTED);
        
    	}catch(Exception e) {
    		
    		return new ResponseEntity<AuthResponse>(HttpStatus.BAD_REQUEST);
    		
    	}
    }
	
}
