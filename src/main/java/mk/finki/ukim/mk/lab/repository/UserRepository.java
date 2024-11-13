package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<>();
    {
        users.add(new User("stefan.kolov","sk","Stefan","Kolov"));
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst();
    }

    public User saveOrUpdate(User user) {
        users.removeIf(r -> r.getUsername().equals(user.getUsername()));
        users.add(user);
        return user;
    }

    void addEvent (EventBooking event, User user) {
        users.stream().filter(r -> r.getUsername().equals(user.getUsername())).findFirst().ifPresent(u -> u.addEvent(event));
    }
}
