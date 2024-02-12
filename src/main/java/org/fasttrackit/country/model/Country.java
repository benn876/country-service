package org.fasttrackit.country.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@With
@Builder(toBuilder = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String capital;
    @Column
    private Integer population;
    @Column
    private Integer aria;
    @Column
    private String continent;
    @Transient
    private List<String> neighbours;
}
