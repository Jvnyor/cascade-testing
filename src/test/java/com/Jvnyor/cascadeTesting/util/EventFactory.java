package com.Jvnyor.cascadeTesting.util;

import com.Jvnyor.cascadeTesting.dtos.EventDto;
import com.Jvnyor.cascadeTesting.entities.Event;
import com.Jvnyor.cascadeTesting.entities.Section;
import com.Jvnyor.cascadeTesting.entities.SubSection;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Set;

public class EventFactory {
    private EventFactory() {}

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static EventDto createEventDto() throws Exception {
        return objectMapper.readValue(new File("src/test/resources/Event.json"), EventDto.class);
    }

    public static Event createEvent() {
        var event = new Event();
        event.setEventId("ABC1");
        event.setName("Test");
        var section = new Section();
        section.setName("Test");
        var subSection1 = new SubSection();
        subSection1.setName("A");
        var subSection2 = new SubSection();
        subSection2.setName("B");
        var subSection3 = new SubSection();
        subSection3.setName("C");
        section.addSubSections(Set.of(subSection1, subSection2, subSection3));
        event.setSection(section);
        return event;
    }
}
