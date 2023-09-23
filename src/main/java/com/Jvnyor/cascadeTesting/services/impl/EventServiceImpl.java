package com.Jvnyor.cascadeTesting.services.impl;

import com.Jvnyor.cascadeTesting.dtos.EventDto;
import com.Jvnyor.cascadeTesting.entities.Event;
import com.Jvnyor.cascadeTesting.mappers.EventMapper;
import com.Jvnyor.cascadeTesting.repositories.EventRepository;
import com.Jvnyor.cascadeTesting.services.EventService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    @Autowired
    public EventServiceImpl(final EventRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void create(EventDto dto) {
        log.info("Going to create an event");
        try {
            var eventFromDto = EventMapper.createEventFromDto(dto);
            var event = repository.save(eventFromDto);
            log.info("Event: {}", event);
            log.info("Section: {}", event.getSection());
            log.info("SubSections: {}", event.getSection().getSubSections());
            log.info("Finish event creation");
        } catch (Exception exception) {
            log.error("An exception occurred while event creation", exception);
            throw exception;
        }
    }

    @Transactional
    @Override
    public void update(Event existingEvent, EventDto dto) {
        log.info("Going to update an event");
        try {
            var updatedEventFromDto = EventMapper.updateEventFromDto(existingEvent, dto);
            var event = repository.save(updatedEventFromDto);
            log.info("Event: {}", event);
            log.info("Section: {}", event.getSection());
            log.info("SubSections: {}", event.getSection().getSubSections());
            log.info("Finish event update");
        } catch (Exception exception) {
            log.error("An exception occurred while event update", exception);
            throw exception;
        }
    }

    @Override
    public Optional<Event> findByEventId(String eventId) {
        log.info("Going to fetch an event by eventId: {}", eventId);
        var optionalEvent = repository.findByEventId(eventId);
        log.info("Event exists: {}", optionalEvent.isPresent());
        return optionalEvent;
    }
}
