package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.ErrorManagement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"errorCode","errorType","errorMessage"})
public class RestErrorTemplate {
    private int errorCode;
    private String errorType;
    private String errorMessage;

    public RestErrorTemplate() {
    }

    public RestErrorTemplate(int errorCode, String errorType, String messgae) {
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.errorMessage = messgae;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
