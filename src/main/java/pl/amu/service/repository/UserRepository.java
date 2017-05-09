package pl.amu.service.repository;

import org.springframework.stereotype.Component;
import pl.amu.service.rest.UsersService;
import pl.amu.service.rest.dao.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserRepository implements UsersService {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        save("mieszko", new User("Mieszko Pierwszy", "mieszko", "mieszko1@o2.pl", Arrays.asList("król") ));
        save("bolek", new User("Bolesław Chrobry", "bolek", "bolek@wp.pl", Arrays.asList("król") ));
        save("janek", new User("Jancio Wodnik", "janek", "janw@gmail.com", Arrays.asList("postać") ));
    }

    public List<User> getUsers(){
        return users;
    }
    
    public User save(String login, User user){
        User dbUser = findByLogin(login);

        if(dbUser == null) {
            dbUser = user;

            users.add(user);
        }

        dbUser.setLogin(login);
        dbUser.setTags(user.getTags());
        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());


        return dbUser;
    }


    public User findByLogin(String login) {
        for(User user : users){
            if (login.equalsIgnoreCase(user.getLogin())){
                return user;
            }
        }
        return null;
    }
    
    public User remove(String login){
        User user = findByLogin(login);
        if (user != null){
            users.remove(user);
        }

        return user;
    }
}
