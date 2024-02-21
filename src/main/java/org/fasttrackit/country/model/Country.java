package org.fasttrackit.country.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private President president;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<City> cities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Country> neighboursCountries;

    @Transient
    private List<String> neighbours;
}
