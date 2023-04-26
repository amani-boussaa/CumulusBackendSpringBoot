package com.example.cumulusspringboot.services.oubaid;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    private final JdbcTemplate jdbcTemplate;

    public SequenceGeneratorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int generateSequence(String seqName) {
        String sql = "UPDATE sequences SET seq = LAST_INSERT_ID(seq + 1) WHERE name = ?";
        jdbcTemplate.update(sql, seqName);
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

}
