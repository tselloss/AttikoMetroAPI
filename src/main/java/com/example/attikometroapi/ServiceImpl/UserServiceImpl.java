package com.example.attikometroapi.ServiceImpl;

import com.example.attikometroapi.Exceptions.CustomerException;
import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Model.RegisterUser;
import com.example.attikometroapi.Repository.CurrentUserSessionRepo;
import com.example.attikometroapi.Repository.UserRepo;
import com.example.attikometroapi.Service.CurrentCustomerService;
import com.example.attikometroapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CurrentCustomerService currentCustomerService;


    //todo
    @Autowired
    CurrentUserSessionRepo currentUserSessionRepo;

    @Override
    public RegisterUser addUser(RegisterUser registerUser) throws CustomerException {
       Optional<RegisterUser> opt = userRepo.findByUsername(registerUser.getUsername()) ;
        if(opt.isPresent()) {
            throw new CustomerException("RegisterUser already Exist With this Username");
        }

        return userRepo.save(registerUser);
    }

    @Override
    public RegisterUser updateUser(RegisterUser registerUser, String key) throws CustomerException, LoginException {

        RegisterUser registerUserDetails = currentCustomerService.getUserDetails(key) ;

        if(registerUserDetails == null) {
            throw new LoginException("No user Found | Login first");
        }else if( registerUser.getMobile_number().toCharArray().length != 10 ){

            throw new CustomerException("Mobile Number can only be of 10 digit");
        }

        if(registerUser.getUserId() == registerUserDetails.getUserId()) {
            return userRepo.save(registerUser) ;
        }
        else {
            throw new CustomerException("Can't change UserID!") ;
        }


    }
    @Override
    public RegisterUser getUserDetailsByUsername(String username) throws Exception {
        return userRepo.findByUsername(username)
                .orElseThrow(
                        () -> new Exception("RegisterUser not found with username: " + username)
                );
    }

    @Override
    public RegisterUser removeUser(RegisterUser registerUser, String key) throws CustomerException, LoginException {
        return null;
    }

    @Override
    public RegisterUser viewUser(Integer userId) throws CustomerException {
        Optional<RegisterUser> cust = userRepo.findById(userId);
        cust.orElseThrow(()-> new CustomerException("RegisterUser doesn't found..."));
        return cust.get();

    }

    @Override
    public List<RegisterUser> viewAllUser() throws CustomerException {
        return userRepo.findAll();
    }

}
