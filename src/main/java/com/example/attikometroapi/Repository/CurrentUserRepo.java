package com.example.attikometroapi.Repository;

import com.example.attikometroapi.Model.CurrentUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrentUserRepo extends JpaRepository<CurrentUser, Integer> {

    public Optional<CurrentUser> findByCustomerId(Integer customerId);

    public Optional<CurrentUser> findByUuid(String uuid);
}
