package com.example.hexagonal.adapter.out.persistence.buildcase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BuildCaseFileRepository extends JpaRepository<BuildCaseFileJpaEntity, Long> {
    BuildCaseFileJpaEntity findByBuildCaseBuildCaseId(Long buildCaseId);
    void deleteByBuildCaseBuildCaseId(Long buildCaseId);
}
