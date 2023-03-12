package com.example.attikometroapi.Repository;

import com.example.attikometroapi.Model.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<RegisterUser, Integer> {
    public Optional<RegisterUser> findByUsername(String username) ;
    public Optional<RegisterUser> findCustomerByUsername(RegisterUser username);

}