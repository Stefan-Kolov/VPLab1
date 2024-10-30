package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidTicketsException;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) throws InvalidTicketsException;

}
