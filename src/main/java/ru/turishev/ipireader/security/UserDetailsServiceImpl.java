package ru.turishev.ipireader.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.model.User;
import ru.turishev.ipireader.repositories.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UsersRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findOneByLogin(username).orElseThrow(()->new UsernameNotFoundException("User " + username +" не найден"));
        UserDetails userDetails = new UserDetailsImpl(user);
        return userDetails;
    }
}
