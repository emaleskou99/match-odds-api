package com.example.matchoddsapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specifier;
    private BigDecimal odd;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSpecifier() { return specifier; }
    public void setSpecifier(String specifier) { this.specifier = specifier; }

    public BigDecimal getOdd() { return odd; }
    public void setOdd(BigDecimal odd) { this.odd = odd; }

    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
}
