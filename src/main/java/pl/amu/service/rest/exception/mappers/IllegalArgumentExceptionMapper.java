package pl.amu.service.rest.exception.mappers;

import pl.amu.service.rest.dao.ErrorMessage;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Optional;

public class IllegalArgumentExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(
                        new ErrorMessage( getMessage(exception), "WRONG_INPUT_REQUEST" )
                ).build();
    }

    private String getMessage(ConstraintViolationException exception) {
        String message = "";
        Optional<ConstraintViolation<?>> first = exception.getConstraintViolations().stream().findFirst();

        if( first.isPresent() )
        {
            message = first.get().getPropertyPath() + " " + first.get().getMessage();
        }

        return message;
    }
}
