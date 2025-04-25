package com.example.matchoddsapi.controller;

import com.example.matchoddsapi.model.Match;
import com.example.matchoddsapi.repository.MatchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchRepository repository;

    public MatchController(MatchRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Match> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Match create(@RequestBody Match match) {
        return repository.save(match);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> update(@PathVariable Long id, @RequestBody Match match) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setDescription(match.getDescription());
                    existing.setMatchDate(match.getMatchDate());
                    existing.setMatchTime(match.getMatchTime());
                    existing.setTeamA(match.getTeamA());
                    existing.setTeamB(match.getTeamB());
                    existing.setSport(match.getSport());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
