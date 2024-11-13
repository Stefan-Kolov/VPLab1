package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private List<EventBooking> events;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.events = new ArrayList<>();
    }

    public void addEvent(EventBooking event) {
        events.add(event);
    }
}
