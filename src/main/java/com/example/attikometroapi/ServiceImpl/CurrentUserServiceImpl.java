package com.example.attikometroapi.ServiceImpl;

import com.example.attikometroapi.Model.CurrentUser;
import com.example.attikometroapi.Model.UserRegistration;
import com.example.attikometroapi.Repository.CurrentUserRepo;
import com.example.attikometroapi.Repository.UserRegistrationRepo;
import com.example.attikometroapi.Service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {
    @Autowired
    private CurrentUserRepo currentUserRepo;

    @Autowired
    private UserRegistrationRepo userRegistrationRepo;


    @Override
    public CurrentUser getCurrentCustomerSession(String key) throws Exception {
        Optional<CurrentUser> currentUser= currentUserRepo.findByUuid(key);
        if(!currentUser.isPresent())
        {
            throw new Exception("User han not logged in");
        }
        return currentUser.get();
    }

    @Override
    public Integer getCurrentCustomerId(String key) throws Exception {
        Optional<CurrentUser> currentUser = currentUserRepo.findByUuid(key);
        if(!currentUser.isPresent()) {
            throw new Exception("User has not logged in");
        }
        return currentUser.get().getCustomerId();
    }

    @Override
    public UserRegistration getCustomerDetails(String key) throws Exception {
        Optional<CurrentUser> currentUser = currentUserRepo.findByUuid(key);
        if(!currentUser.isPresent()) {
            throw new Exception("User has not logged in");
        }
        Integer customerId = currentUser.get().getCustomerId();
        return userRegistrationRepo.findById(customerId).get();
    }
}
