package com.Jvnyor.cascadeTesting.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "sqSubSection", sequenceName = "sq_sub_section", allocationSize = 1)
public class SubSection {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqSubSection")
    @Column(nullable = false)
    private Long id;
    private String name;

}
