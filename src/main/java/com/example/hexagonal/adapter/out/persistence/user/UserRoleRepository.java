package com.example.hexagonal.adapter.out.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface UserRoleRepository extends JpaRepository<UserRoleJpaEntity, Long> {
    List<UserRoleJpaEntity> findByUserJpaEntityUserId(Long userId);
    void deleteAllByUserJpaEntityUserId(Long userId);

}
