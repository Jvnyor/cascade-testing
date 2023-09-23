package com.Jvnyor.cascadeTesting.services;

import com.Jvnyor.cascadeTesting.dtos.EventDto;
import com.Jvnyor.cascadeTesting.entities.Event;

import java.util.Optional;

public interface EventService {
    void create(EventDto dto);
    void update(Event existingEvent, EventDto dto);
    Optional<Event> findByEventId(String eventId);
}
