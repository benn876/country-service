package org.fasttrackit.country.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.country.model.President;
import org.fasttrackit.country.service.PresidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("presidents")
@RequiredArgsConstructor
public class PresidentController {
    private final PresidentService service;

    @GetMapping
    public List<President> getAll() {
        return service.getAll();
    }

    @PostMapping
    public President createPresident(@RequestBody President newPresident) {
        return service.createPresident(newPresident);
    }
}
