package org.fasttrackit.curs18.model;

import lombok.Builder;
import lombok.With;

import java.util.List;

@With
@Builder(toBuilder = true)
public record Country(
        String id,
        String name,
        String capital,
        Integer population,
        Integer aria,
        String continent,
        List<String> neighbours
) {
}
