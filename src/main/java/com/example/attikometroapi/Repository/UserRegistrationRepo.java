package com.example.attikometroapi.Repository;

import com.example.attikometroapi.Model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Integer> {
    public Optional<UserRegistration> findByUsername(String username) ;
    public Optional<UserRegistration> findCustomerByUsername(UserRegistration username);

}
