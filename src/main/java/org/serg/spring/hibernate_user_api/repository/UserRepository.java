package org.serg.spring.hibernate_user_api.repository;

import org.serg.spring.hibernate_user_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
