package com.spacex.tickets.services.impl;

import com.spacex.tickets.domain.CreateEventRequest;
import com.spacex.tickets.domain.entities.Event;
import com.spacex.tickets.domain.entities.TicketType;
import com.spacex.tickets.domain.entities.User;
import com.spacex.tickets.exceptions.UserNotFoundException;
import com.spacex.tickets.respository.EventRepository;
import com.spacex.tickets.respository.UserRepository;
import com.spacex.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId).orElseThrow(()-> new UserNotFoundException(
                String.format("User with Id '%s' not found", organizerId)
        ));

       List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(ticketType -> {
            TicketType ticketTypeToCreate = new TicketType();
            ticketTypeToCreate.setName(ticketType.getName());
            ticketTypeToCreate.setPrice(ticketType.getPrice());
            ticketTypeToCreate.setDescription(ticketType.getDescription());
            ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());

            return ticketTypeToCreate;
        }).toList();

        Event eventTOCreate = new Event();
        eventTOCreate.setName(event.getName());
        eventTOCreate.setCreatedAt(event.getStart());
        eventTOCreate.setEnd(event.getEnd());
        eventTOCreate.setVenue(event.getVenue());
        eventTOCreate.setSaleStart(event.getSaleStart());
        eventTOCreate.setSaleEnd(event.getSaleEnd());
        eventTOCreate.setStatus(event.getStatus());
        eventTOCreate.setOrganizer(organizer);
        eventTOCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventTOCreate);

    }
}
