package com.example.attikometroapi.ServiceImpl;

import com.example.attikometroapi.Model.UserRegistration;
import com.example.attikometroapi.Repository.CurrentUserRepo;
import com.example.attikometroapi.Repository.UserRegistrationRepo;
import com.example.attikometroapi.Service.CurrentUserService;
import com.example.attikometroapi.Service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegistrationServiceImpl  implements UserRegistrationService {

    @Autowired
    UserRegistrationRepo userRegistrationRepo;

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    CurrentUserRepo currentUserRepo;


    @Override
    public UserRegistration addUser(UserRegistration userRegistration) throws Exception {
        Optional<UserRegistration> optional= userRegistrationRepo.findByUsername(userRegistration.getUsername());
        if(optional.isPresent())
        {
            throw  new Exception("User with this username already exists!");
        }
        return userRegistrationRepo.save(userRegistration);
    }

    @Override
    public UserRegistration updateUser(UserRegistration userRegistration, String key) throws Exception {
        UserRegistration userDetails= currentUserService.getCustomerDetails(key);
        if(userDetails == null) {
            throw new Exception("No user Found | Login first");
        }else if( userRegistration.getPassword().toCharArray().length <8 ){

            throw new Exception("Pass can only >=8");
        }

        if(userRegistration.getUserId() == userDetails.getUserId()) {
            return userRegistrationRepo.save(userRegistration) ;
        }
        else {
            throw new Exception("Can't change UserID!") ;
        }
    }

    @Override
    public UserRegistration removeUser(UserRegistration userRegistration, String key) throws Exception {
        return null;
    }

    @Override
    public UserRegistration viewUser(Integer userId) throws Exception {
        Optional<UserRegistration> userRegistration = userRegistrationRepo.findById(userId);
        userRegistration.orElseThrow(()-> new Exception("Customer doesn't found..."));
        return userRegistration.get();
    }

    @Override
    public List<UserRegistration> viewAllUser() throws Exception {
        return userRegistrationRepo.findAll();
    }

    @Override
    public UserRegistration getUserDetailsByUsername(String username) throws Exception {
        return userRegistrationRepo.findByUsername(username)
                .orElseThrow(
                        () -> new Exception("Customer not found with username: " + username)
                );
    }
}
