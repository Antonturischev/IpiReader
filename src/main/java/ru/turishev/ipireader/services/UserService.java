package ru.turishev.ipireader.services;

import org.springframework.stereotype.Service;
import ru.turishev.ipireader.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
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
