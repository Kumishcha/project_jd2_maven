package by.htp.jd2.service.validation;

public class ValidationServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidationServiceException() {
		super();
	}

	public ValidationServiceException(String message) {
		super(message);
	}

	public ValidationServiceException(Exception e) {
		super(e);
	}

	public ValidationServiceException(String message, Exception e) {
		super(message, e);
	}

}
