package pl.amu.service.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.amu.service.rest.dao.ErrorMessage;
import pl.amu.service.rest.dao.User;
import pl.amu.service.rest.exception.UsersAppExceptions;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Api
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Autowired
    private UsersService usersService;

    private static Logger logger = LoggerFactory.getLogger(UsersResource.class);

    @GET
    public Response getUsers() {
        logger.info("GET /users");

        return Response.ok(usersService.getUsers())
                .build();
    }

    @POST
    @ApiOperation("Creates new user")
    public Response registerUser(@Valid User user) throws UsersAppExceptions {
        if (usersService.findByLogin(user.getLogin()) == null) {
            return Response
                    .ok(usersService.save(user.getLogin(), user))
                    .build();
        }

        throw new UsersAppExceptions("Użytkownik " + user.getLogin() + " już jest zarejestrowany", "USER_ALREADY_REGISTERED", Response.Status.CONFLICT);
    }

    @ApiOperation("Updates existsing user data")
    @PUT()
    @Path("/{login}")
    public Response updateUser(@PathParam("login") String login, User user) throws UsersAppExceptions {
        if (usersService.findByLogin(login) == null) {
            throw new UsersAppExceptions("Użytkownik " + login + " nie znaleziony", "USER_NOT_FOUND", Response.Status.CONFLICT);
        }

        return Response.ok(usersService.save(login, user)).build();
    }

    @ApiOperation("Return user with given login")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Returned when user with given login doesn't exist", response = ErrorMessage.class)
    })
    @GET
    @Path("/{login}")
    public Response getUser(@PathParam("login") final String login, @Context  Request request) throws UsersAppExceptions {
        logger.info("GET /users/" + login);



        return Response.ok(usersService.findByLogin(login))
                .build();
    }

    @ApiOperation("Removes user from database")
    @DELETE
    @Path("/{login}")
    public void deleteUser(@PathParam("login") final String login) throws UsersAppExceptions {
        User user = usersService.remove(login);

        if (user == null) {
            throw new UsersAppExceptions("Użytkownik " + login + " nie znaleziony", "USER_NOT_FOUND", Response.Status.NOT_FOUND);
        }
    }

}
