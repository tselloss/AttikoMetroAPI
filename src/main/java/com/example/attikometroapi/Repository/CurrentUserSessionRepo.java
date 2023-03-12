package com.example.attikometroapi.Repository;

import com.example.attikometroapi.Model.CurrentUserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession, Integer> {

    public Optional<CurrentUserSession> findByCustomerId(Integer customerId) ;

    public Optional<CurrentUserSession> findByUuid(String uuid) ;

}
