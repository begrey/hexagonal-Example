package com.example.hexagonal.adapter.out.persistence.buildcase;

import org.springframework.data.jpa.repository.JpaRepository;

interface BuildCaseRepository extends JpaRepository<BuildCaseJpaEntity, Long> {
}
