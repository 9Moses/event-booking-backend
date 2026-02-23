package com.spacex.tickets.mappers;

import com.spacex.tickets.domain.CreateEventRequest;
import com.spacex.tickets.domain.CreateTicketTypeRequest;
import com.spacex.tickets.domain.dto.*;
import com.spacex.tickets.domain.entities.Event;
import com.spacex.tickets.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDTO dto);

    CreateEventResponseDto toDto(Event event);

    ListEventTicketTypeResponseDto toDto(TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);
}
