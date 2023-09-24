package com.Jvnyor.cascadeTesting.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "sqEvent", sequenceName = "sq_event", allocationSize = 1)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqEvent")
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String eventId;
    private String name;
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

}
