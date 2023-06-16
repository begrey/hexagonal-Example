package com.example.hexagonal.adapter.out.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {

    UserJpaEntity findByMidasUserId(String midasUserId);
    UserJpaEntity findByMidasUserIdAndPassword(String midasUserId, String password);

}
