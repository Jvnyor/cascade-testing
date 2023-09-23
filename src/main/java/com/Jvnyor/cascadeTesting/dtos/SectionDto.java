package com.Jvnyor.cascadeTesting.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "subSections"
})
public class SectionDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("subSections")
    private List<SubSectionDto> subSections;

}
