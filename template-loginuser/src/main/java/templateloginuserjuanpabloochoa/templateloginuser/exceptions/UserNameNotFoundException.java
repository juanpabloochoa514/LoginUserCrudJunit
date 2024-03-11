package templateloginuserjuanpabloochoa.templateloginuser.exceptions;

public class UserNameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserNameNotFoundException() {
		super ();
	}
	
	public UserNameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public UserNameNotFoundException(String message) {
		super(message);
	}
	
}
