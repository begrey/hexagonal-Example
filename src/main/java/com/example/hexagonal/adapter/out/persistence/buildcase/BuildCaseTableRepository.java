package com.example.hexagonal.adapter.out.persistence.buildcase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildCaseTableRepository extends JpaRepository<BuildCaseTableJpaEntity, Long> {
    void deleteByBuildCaseBuildCaseId(Long buildCaseId);
}
