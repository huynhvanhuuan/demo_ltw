package vn.edu.hcmuaf.fit.constant;

public enum AppError {
	
	Validation(101, "Validation error"),
	Unknown(99, "Unknown error");

	private int errorCode;
    private String errorMessage;

    AppError(int errorCode, String errorMessage) {
    	this.errorCode = errorCode;
    	this.errorMessage = errorMessage;
    }
    
    public int errorCode() {
        return errorCode;
    }
    
    public String errorMessage() {
        return errorMessage;
    }
}
