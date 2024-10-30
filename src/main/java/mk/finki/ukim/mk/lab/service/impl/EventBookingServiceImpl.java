package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidTicketsException;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) throws InvalidTicketsException {
        if (numberOfTickets == 0){
            throw new InvalidTicketsException();
        }
        return new EventBooking(eventName,attendeeName,attendeeAddress,(long)numberOfTickets);
    }
}