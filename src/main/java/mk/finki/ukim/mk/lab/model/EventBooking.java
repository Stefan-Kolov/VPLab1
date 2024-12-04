package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class EventBooking {
    @Id
    @GeneratedValue
    private long id;
    String eventName;
    String attendeeName;
    String attendeeAddress;
    Long numberOfTickets;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public EventBooking() {

    }

    public EventBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
