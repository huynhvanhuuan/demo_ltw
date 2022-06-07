package vn.edu.hcmuaf.fit.domain;

public final class AppServiceResult<T> extends AppBaseResult {
    private T data;

    public AppServiceResult() {
    }

    public AppServiceResult(boolean success, int errorCode, String message, T data) {
        super(success, errorCode, message);
        this.data = data;
    }

    public boolean isSuccess() {
        return super.isSuccess();
    }

    public void setSuccess(boolean success) {
        super.setSuccess(success);
    }

    public int getErrorCode() {
        return super.getErrorCode();
    }

    public void setErrorCode(int errorCode) {
        super.setErrorCode(errorCode);
    }

    public String getMessage() {
        return super.getMessage();
    }

    public void setMessage(String message) {
        super.setMessage(message);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
