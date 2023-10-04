package com.example.urltracking.global.model;

import jakarta.persistence.MappedSuperclass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@MappedSuperclass
@EnableJpaAuditing
@Configuration
public class JpaConfig {
}
