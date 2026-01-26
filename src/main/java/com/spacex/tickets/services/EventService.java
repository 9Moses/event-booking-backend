package com.spacex.tickets.services;

import com.spacex.tickets.domain.CreateEventRequest;
import com.spacex.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
}
