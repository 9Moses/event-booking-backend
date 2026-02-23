package com.spacex.tickets.controllers;

import com.spacex.tickets.domain.CreateEventRequest;
import com.spacex.tickets.domain.dto.CreateEventRequestDTO;
import com.spacex.tickets.domain.dto.CreateEventResponseDto;
import com.spacex.tickets.domain.dto.ListEventResponseDto;
import com.spacex.tickets.domain.entities.Event;
import com.spacex.tickets.mappers.EventMapper;
import com.spacex.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDTO createEventRequestDTO
            ){
        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDTO);
        UUID userId = parseUserId(jwt);

        Event createdEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ListEventResponseDto>> listEvents(
            @AuthenticationPrincipal Jwt jwt, Pageable pageable
    ){
       UUID userId = parseUserId(jwt);
       Page<Event> events = eventService.listEventsForOrganizer(userId, pageable);
       return ResponseEntity.ok(events.map(eventMapper::toListEventResponseDto));
    }

    private UUID parseUserId(Jwt jwt){
        return  UUID.fromString(jwt.getSubject());
    }
}
