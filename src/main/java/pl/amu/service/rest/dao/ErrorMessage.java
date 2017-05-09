package pl.amu.service.rest.dao;

public class ErrorMessage {
    private String message;
    private String code;

    public ErrorMessage(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
