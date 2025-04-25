package com.example.matchoddsapi.controller;

import com.example.matchoddsapi.model.MatchOdds;
import com.example.matchoddsapi.repository.MatchOddsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odds")
public class MatchOddsController {

    private final MatchOddsRepository repository;

    public MatchOddsController(MatchOddsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MatchOdds> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchOdds> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MatchOdds create(@RequestBody MatchOdds odds) {
        return repository.save(odds);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchOdds> update(@PathVariable Long id, @RequestBody MatchOdds odds) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setSpecifier(odds.getSpecifier());
                    existing.setOdd(odds.getOdd());
                    existing.setMatch(odds.getMatch());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
