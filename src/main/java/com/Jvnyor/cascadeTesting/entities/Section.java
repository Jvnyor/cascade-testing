package com.Jvnyor.cascadeTesting.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "sqSection", sequenceName = "sq_section", allocationSize = 1)
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqSection")
    @Column(nullable = false)
    private Long id;
    private String name;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "section_id", nullable = false)
    private Set<SubSection> subSections = new LinkedHashSet<>();

    public void addSubSections(Set<SubSection> subSections) {
        this.subSections.addAll(subSections);
    }

}
