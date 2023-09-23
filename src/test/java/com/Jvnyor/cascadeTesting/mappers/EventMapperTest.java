package com.Jvnyor.cascadeTesting.mappers;

import com.Jvnyor.cascadeTesting.dtos.EventDto;
import com.Jvnyor.cascadeTesting.dtos.SubSectionDto;
import com.Jvnyor.cascadeTesting.entities.Event;
import com.Jvnyor.cascadeTesting.entities.SubSection;
import com.Jvnyor.cascadeTesting.util.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EventMapperTest {

    private EventDto eventDto;
    private Event existingEvent;

    @BeforeEach
    void setUp() throws Exception {
        this.eventDto = EventFactory.createEventDto();
        this.existingEvent = EventFactory.createEvent();
    }

    @Test
    void givenDto_whenCreateEventFromDto_thenEventIsReturned() {
        var eventFromDto = EventMapper.createEventFromDto(eventDto);
        var sectionFromDto = eventFromDto.getSection();
        var subSectionsFromDto = sectionFromDto.getSubSections().stream().toList();
        assertAll(
                () -> assertEquals(eventDto.getEventId(), eventFromDto.getEventId()),
                () -> assertEquals(eventDto.getName(), eventFromDto.getName()),
                () -> assertEquals(eventDto.getSection().getName(), sectionFromDto.getName()),
                () -> assertEquals(eventDto.getSection().getSubSections().get(0).getName(), subSectionsFromDto.get(0).getName()),
                () -> assertEquals(eventDto.getSection().getSubSections().get(1).getName(), subSectionsFromDto.get(1).getName()),
                () -> assertEquals(eventDto.getSection().getSubSections().get(2).getName(), subSectionsFromDto.get(2).getName())
        );
    }

    @Test
    void givenExistingEventAndDto_whenUpdateEventFromDto_thenEventIsReturned() {
        var eventFromDto = EventMapper.updateEventFromDto(existingEvent, eventDto);
        var sectionFromDto = eventFromDto.getSection();
        var subSectionsFromDto = sectionFromDto.getSubSections().stream().sorted(Comparator.comparing(SubSection::getName)).toList();
        var subSectionsDtoList = eventDto.getSection().getSubSections().stream().sorted(Comparator.comparing(SubSectionDto::getName)).toList();
        assertAll(
                () -> assertEquals(eventDto.getEventId(), eventFromDto.getEventId()),
                () -> assertEquals(eventDto.getName(), eventFromDto.getName()),
                () -> assertEquals(eventDto.getSection().getName(), sectionFromDto.getName()),
                () -> assertEquals(subSectionsDtoList.get(0).getName(), subSectionsFromDto.get(0).getName()),
                () -> assertEquals(subSectionsDtoList.get(1).getName(), subSectionsFromDto.get(1).getName()),
                () -> assertEquals(subSectionsDtoList.get(2).getName(), subSectionsFromDto.get(2).getName())
        );
    }
}