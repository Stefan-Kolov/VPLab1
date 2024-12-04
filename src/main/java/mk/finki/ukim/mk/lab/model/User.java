package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private String username;
    private String password;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "user")
    private List<EventBooking> events;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.events = new ArrayList<>();
    }

    public User() {

    }

    public void addEvent(EventBooking event) {
        events.add(event);
    }
}
