package com.Jvnyor.cascadeTesting;

import com.Jvnyor.cascadeTesting.dtos.EventDto;
import com.Jvnyor.cascadeTesting.services.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;

@Order(0)
@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    private EventService service;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        var dto1 = createEventDto1();
        service.create(dto1);
        var dto2 = createEventDto2();
        var optionalEvent = service.findByEventId(dto2.getEventId());
        optionalEvent.ifPresentOrElse(existingEvent -> service.update(existingEvent, dto2), () -> service.create(dto2));
    }

    private static EventDto createEventDto1() throws Exception {
        return objectMapper.readValue(new File("src/main/resources/Event1.json"), EventDto.class);
    }

    private static EventDto createEventDto2() throws Exception {
        return objectMapper.readValue(new File("src/main/resources/Event2.json"), EventDto.class);
    }

}