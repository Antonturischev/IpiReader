package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.model.User;
import ru.turishev.ipireader.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public User getUserByLogin(String login) {
        return usersRepository.findOneByLogin(login).orElseThrow(IllegalArgumentException::new);
    }

    public static List<User> getChildSubordinates(User user) {
        List<User> result = new ArrayList<User>();
        result.add(user);
        if(user.getUnderUsers()!=null) {
            for(User usr : user.getUnderUsers()) {
                result.addAll(UserService.getChildSubordinates(usr));
            }
        }
        return result;
    }
}
