package com.Jvnyor.cascadeTesting.repositories;

import com.Jvnyor.cascadeTesting.util.EventFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class EventRepositoryTest {

    public static final String EXISTENT_EVENT_ID = "ABC1";
    public static final String NON_EXISTENT_EVENT_ID = "ABC2";
    @Autowired
    private EventRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        var event = EventFactory.createEvent();
        repository.save(event);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void givenExistentEventId_whenFindByEventId_thenReturnAPresentOptional() {
        var optionalEvent = repository.findByEventId(EXISTENT_EVENT_ID);
        assertTrue(optionalEvent.isPresent());
    }

    @Test
    void givenNonExistentEventId_whenFindByEventId_thenReturnAnEmptyOptional() {
        var optionalEvent = repository.findByEventId(NON_EXISTENT_EVENT_ID);
        assertTrue(optionalEvent.isEmpty());
    }
}