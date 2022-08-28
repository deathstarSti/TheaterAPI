package Theater.JSON_REST;

import org.springframework.http.HttpStatus;

public class Message {
	
	String message;
	HttpStatus errorCode;
	
	public Message(String message, HttpStatus errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	
	

}
