package com.vasiliyzhigalov.repository;

import com.vasiliyzhigalov.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
}
