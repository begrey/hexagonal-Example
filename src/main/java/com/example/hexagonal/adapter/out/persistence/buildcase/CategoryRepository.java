package com.example.hexagonal.adapter.out.persistence.buildcase;

import com.example.hexagonal.adapter.out.persistence.buildcase.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CategoryRepository extends JpaRepository<CategoryJpaEntity, Long> {
}
