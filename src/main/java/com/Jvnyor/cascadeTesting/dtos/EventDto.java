package com.Jvnyor.cascadeTesting.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "eventId",
    "name",
    "section"
})
public class EventDto {

    @JsonProperty("eventId")
    private String eventId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("section")
    private SectionDto section;

}
