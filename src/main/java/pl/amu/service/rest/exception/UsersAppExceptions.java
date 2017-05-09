package pl.amu.service.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class UsersAppExceptions extends Exception {
    private final Status httpStatus;
    private final String code;

    public UsersAppExceptions(String message, String code, Status httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public Status getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }
}
