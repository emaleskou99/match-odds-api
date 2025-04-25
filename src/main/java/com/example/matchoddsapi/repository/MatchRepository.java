package com.example.matchoddsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.matchoddsapi.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
