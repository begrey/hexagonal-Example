package com.example.hexagonal.adapter.out.persistence.userlog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLogJpaEntity, Long> {
    Page<UserLogJpaEntity> findByMidasUserId(String keyword, Pageable pageable);
}
