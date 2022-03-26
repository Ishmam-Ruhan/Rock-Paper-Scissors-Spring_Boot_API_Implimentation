package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.ErrorManagement;

public class CustomizeException extends Exception{
    private int error_code;
    private String error_type;
    private String message;

    public CustomizeException(int error_code, String error_type, String message) {
        this.error_code = error_code;
        this.error_type = error_type;
        this.message = message;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_type() {
        return error_type;
    }

    public void setError_type(String error_type) {
        this.error_type = error_type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
