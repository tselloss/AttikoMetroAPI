package com.example.attikometroapi.ServiceImpl;

import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Model.CurrentUserSession;
import com.example.attikometroapi.Model.RegisterUser;
import com.example.attikometroapi.Repository.CurrentUserSessionRepo;
import com.example.attikometroapi.Repository.UserRepo;
import com.example.attikometroapi.Service.CurrentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentCustomerServiceImpl implements CurrentCustomerService {

    @Autowired
    private CurrentUserSessionRepo currentUserSessionRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public CurrentUserSession getCurrentCustomerSession(String key) throws LoginException {
        Optional<CurrentUserSession> currentUser = currentUserSessionRepo.findByUuid(key);
        if(!currentUser.isPresent()) {
            throw new  LoginException("User has not logged in");
        }
        return currentUser.get();
    }

    @Override
    public Integer getCurrentCustomerId(String key) throws LoginException {
        Optional<CurrentUserSession> currentUser = currentUserSessionRepo.findByUuid(key);
        if(!currentUser.isPresent()) {
            throw new  LoginException("User has not logged in");
        }
        return currentUser.get().getCustomerId();
    }

    @Override
    public RegisterUser getCustomerDetails(String key) throws LoginException {
        Optional<CurrentUserSession> currentUser = currentUserSessionRepo.findByUuid(key);
        if(!currentUser.isPresent()) {
            throw new  LoginException("User has not logged in");
        }
        Integer customerId = currentUser.get().getCustomerId();
        return userRepo.findById(customerId).get();
    }

}

