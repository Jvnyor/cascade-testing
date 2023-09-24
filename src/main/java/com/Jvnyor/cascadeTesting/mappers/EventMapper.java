package com.Jvnyor.cascadeTesting.mappers;

import com.Jvnyor.cascadeTesting.dtos.EventDto;
import com.Jvnyor.cascadeTesting.dtos.SectionDto;
import com.Jvnyor.cascadeTesting.dtos.SubSectionDto;
import com.Jvnyor.cascadeTesting.entities.Event;
import com.Jvnyor.cascadeTesting.entities.Section;
import com.Jvnyor.cascadeTesting.entities.SubSection;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EventMapper {
    private EventMapper() {}

    public static Event createEventFromDto(EventDto dto) {
        var event = new Event();
        event.setEventId(dto.getEventId());
        event.setName(dto.getName());
        event.setSection(createSectionFromDto(dto.getSection()));
        return event;
    }

    public static Event updateEventFromDto(Event existingEvent, EventDto dto) {
        existingEvent.setName(dto.getName());
        existingEvent.setSection(createSectionFromDto(dto.getSection()));
        return existingEvent;
    }

    private static Section createSectionFromDto(SectionDto dto) {
        var section = new Section();
        section.setName(dto.getName());
        section.addSubSections(createSubSectionsFromDto(dto.getSubSections()));
        return section;
    }

    private static Set<SubSection> createSubSectionsFromDto(List<SubSectionDto> dtoList) {
        return dtoList.stream().map(dto -> {
            var subSection = new SubSection();
            subSection.setName(dto.getName());
            return subSection;
        }).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
