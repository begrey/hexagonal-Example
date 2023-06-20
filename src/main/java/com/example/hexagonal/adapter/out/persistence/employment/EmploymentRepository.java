package com.example.hexagonal.adapter.out.persistence.employment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EmploymentRepository extends JpaRepository<EmploymentJpaEntity, Long> {
}
