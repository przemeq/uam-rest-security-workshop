package pl.amu.service.rest.exception.mappers;

import pl.amu.service.rest.dao.ErrorMessage;
import pl.amu.service.rest.exception.UsersAppExceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UsersAppExceptionMapper implements ExceptionMapper<UsersAppExceptions> {

    @Override
    public Response toResponse(UsersAppExceptions exception) {
        return Response.status(exception.getHttpStatus())
                .entity(
                        new ErrorMessage(exception.getMessage(), exception.getCode())
                )
                .build();
    }
}
