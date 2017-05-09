package pl.amu.service.rest;

import pl.amu.service.rest.dao.User;

import java.util.List;

public interface UsersService {
    List<User> getUsers();
    User findByLogin(String login);
    User remove(String login);
    User save(String login, User user);
}
