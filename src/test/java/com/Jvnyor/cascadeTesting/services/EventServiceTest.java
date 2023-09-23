package com.Jvnyor.cascadeTesting.services;

import com.Jvnyor.cascadeTesting.dtos.EventDto;
import com.Jvnyor.cascadeTesting.entities.Event;
import com.Jvnyor.cascadeTesting.mappers.EventMapper;
import com.Jvnyor.cascadeTesting.repositories.EventRepository;
import com.Jvnyor.cascadeTesting.services.impl.EventServiceImpl;
import com.Jvnyor.cascadeTesting.util.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository repository;
    @InjectMocks
    private EventServiceImpl service;
    private EventDto dto;
    private Event event;

    @BeforeEach
    void setUp() throws Exception {
        this.dto = EventFactory.createEventDto();
        this.event = EventMapper.createEventFromDto(dto);
    }

    @Test
    void givenDto_whenCreateIsCalled_thenNoExceptionIsThrown() {
        when(repository.save(any(Event.class))).thenReturn(event);
        assertDoesNotThrow(() -> service.create(dto));
    }

    @Test
    void givenDatabaseRejection_whenCreateIsCalled_thenExceptionIsThrown() {
        when(repository.save(any(Event.class))).thenThrow(DataIntegrityViolationException.class);
        assertThrows(Exception.class, () -> service.create(dto));
    }

    @Test
    void givenExistingEventAndDto_whenUpdateIsCalled_thenNoExceptionIsThrown() {
        when(repository.save(any(Event.class))).thenReturn(event);
        assertDoesNotThrow(() -> service.update(event, dto));
    }

    @Test
    void givenDatabaseRejection_whenUpdateIsCalled_thenExceptionIsThrown() {
        when(repository.save(any(Event.class))).thenThrow(DataIntegrityViolationException.class);
        assertThrows(Exception.class, () -> service.update(event, dto));
    }

    @Test
    void givenExistingEventId_whenFindByEventIdIsCalled_thenReturnAPresentOptional() {
        when(repository.findByEventId(anyString())).thenReturn(Optional.of(event));
        var optionalEvent = service.findByEventId("ABC1");
        assertTrue(optionalEvent.isPresent());
    }

    @Test
    void givenNonExistingEventId_whenFindByEventIdIsCalled_thenReturnAnEmptyOptional() {
        when(repository.findByEventId(anyString())).thenReturn(Optional.empty());
        var optionalEvent = service.findByEventId("ABC2");
        assertTrue(optionalEvent.isEmpty());
    }
}