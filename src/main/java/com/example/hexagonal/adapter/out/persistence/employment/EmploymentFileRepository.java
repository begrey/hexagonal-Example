package com.example.hexagonal.adapter.out.persistence.employment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EmploymentFileRepository extends JpaRepository<EmploymentFileJpaEntity, Long> {
    void deleteByEmploymentEmploymentId(Long employmentId);
    EmploymentFileJpaEntity findByEmploymentEmploymentId(Long employmentId);
}
