package com.example.attikometroapi.ServiceImpl;


import com.example.attikometroapi.Exceptions.CustomerException;
import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Exceptions.UserException;
import com.example.attikometroapi.Model.CurrentUserSession;
import com.example.attikometroapi.Model.RegisterUser;
import com.example.attikometroapi.Model.User;
import com.example.attikometroapi.Repository.CurrentUserSessionRepo;
import com.example.attikometroapi.Repository.UserRepo;
import com.example.attikometroapi.Service.CurrentCustomerService;
import com.example.attikometroapi.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CurrentUserSessionRepo currentUserSessionRepo;

    @Autowired
    CurrentCustomerService currentCustomerService;

    @Override
    public CurrentUserSession addUser(User user) throws UserException, CustomerException {
        Optional<RegisterUser> opt = userRepo.findByUsername(user.getUserId()) ;
        if(opt.isEmpty()) {
            throw new CustomerException("User with Mobile number NOT FOUND: "+user.getUserId());
        }
        RegisterUser currentRegisterUser = opt.get();
        Integer customerId = currentRegisterUser.getCustomerId();
        Optional<CurrentUserSession> currentUserOptional = currentUserSessionRepo.findByCustomerId(customerId);
        if(currentUserOptional.isPresent()) {
            throw new UserException("User: " + user.getUserId()+" has already logged in!!!");
        }
        if(currentRegisterUser.getMobile_number().equals(user.getUserId()) && currentRegisterUser.getPassword().equals(user.getPassword())) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
            String key = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            CurrentUserSession currentUserSession = new CurrentUserSession(customerId, key, LocalDateTime.now()) ;
            return  currentUserSessionRepo.save(currentUserSession) ;
        }
        else {
            throw new UserException("Invalid UserId OR Password");
        }
    }



    @Override
    public String signOut(String key) throws UserException, LoginException {
        CurrentUserSession userSession = currentCustomerService.getCurrentCustomerSession(key);

        if(userSession != null) {

            currentUserSessionRepo.delete(userSession);


            return "Logged out...";
        }
        else {
            throw new UserException("Having some problem to logout");
        }
    }

    @Override
    public User removeUser(User user, String key) throws UserException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User validateUser(User user, String key) throws UserException {

        Optional<CurrentUserSession> opt = currentUserSessionRepo.findByUuid(key) ;

        if(opt.isEmpty()) {
            throw new UserException("Invalid Key");
        }

        CurrentUserSession currentUserSession = opt.get();

        Optional<RegisterUser> currentCustomerOpt = userRepo.findById(currentUserSession.getCustomerId()) ;

        RegisterUser currentRegisterUser = currentCustomerOpt.get();

        if(user.getUserId().equals(currentRegisterUser.getMobile_number()) && user.getPassword().equals(currentRegisterUser.getPassword())) {
            return user;
        }
        else {
            throw new UserException("Invalid Mobile Number or Password");
        }


    }

}

