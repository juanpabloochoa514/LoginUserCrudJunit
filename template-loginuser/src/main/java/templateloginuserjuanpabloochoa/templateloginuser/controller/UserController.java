package templateloginuserjuanpabloochoa.templateloginuser.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import templateloginuserjuanpabloochoa.templateloginuser.service.LoginRequest;
import templateloginuserjuanpabloochoa.templateloginuser.service.RegisterRequest;
import templateloginuserjuanpabloochoa.templateloginuser.service.UserService;
@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserService userService; 
	
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request)
    {
    	try { 		
            return  ResponseEntity.ok(userService.login(request));      
    	}catch(Exception e) {
    		return  ResponseEntity.status(500).body("Internal error"+e);
    	}
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest)
    {
    	try {		
    		return new ResponseEntity<String>(userService.register(registerRequest),HttpStatus.ACCEPTED); 
    	}catch(Exception e) {
    		return new ResponseEntity<String>("error to register, maybe some one fields its in blank",HttpStatus.BAD_REQUEST);
    	}
    }
}
