package com.spacex.tickets.mappers;

import com.spacex.tickets.domain.CreateEventRequest;
import com.spacex.tickets.domain.CreateTicketTypeRequest;
import com.spacex.tickets.domain.dto.CreateEventRequestDTO;
import com.spacex.tickets.domain.dto.CreateEventResponseDto;
import com.spacex.tickets.domain.dto.CreateTicketTypeRequestDto;
import com.spacex.tickets.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDTO dto);

    CreateEventResponseDto toDto(Event event);
}
