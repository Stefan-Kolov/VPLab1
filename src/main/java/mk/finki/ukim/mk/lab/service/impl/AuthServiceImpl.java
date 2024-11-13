package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException("Username or password is empty");
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow();
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException("Username or password is empty");
        }
        if(!password.equals(repeatPassword)) {
            throw new RuntimeException("Passwords do not match");
        }
        User user = new User(username, password, name, surname);
        return userRepository.saveOrUpdate(user);
    }
}
