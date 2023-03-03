package com.example.attikometroapi.ServiceImpl;

import com.example.attikometroapi.Model.CurrentUser;
import com.example.attikometroapi.Model.UserLogin;
import com.example.attikometroapi.Model.UserRegistration;
import com.example.attikometroapi.Repository.CurrentUserRepo;
import com.example.attikometroapi.Repository.UserRegistrationRepo;
import com.example.attikometroapi.Service.CurrentUserService;
import com.example.attikometroapi.Service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserRegistrationRepo userRegistrationRepo;

    @Autowired
    CurrentUserRepo currentUserRepo;

    @Autowired
    CurrentUserService currentUserService;

    @Override
    public CurrentUser addUser(UserLogin userLogin) throws Exception {
        Optional<UserRegistration> opt = userRegistrationRepo.findByUsername(userLogin.getUserId()) ;
        if(opt.isEmpty()) {
            throw new Exception("User with Mobile number NOT FOUND: "+userLogin.getUserId());
        }
        UserRegistration userRegistration = opt.get();
        Integer customerId = userRegistration.getUserId();
        Optional<CurrentUser> currentUserOptional = currentUserRepo.findByCustomerId(customerId);
        if(currentUserOptional.isPresent()) {
            throw new Exception("User: " + userLogin.getUserId()+" has already logged in!!!");
        }
        if(userRegistration.getEmail().equals(userLogin.getUserId()) && userRegistration.getPassword().equals(userLogin.getPassword())) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
            String key = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            CurrentUser currentUserSession = new CurrentUser(customerId, key, LocalDateTime.now()) ;
            return  currentUserRepo.save(currentUserSession) ;
        }
        else {
            throw new Exception("Invalid UserId OR Password");
        }
    }

    @Override
    public UserLogin removeUser(UserLogin userLogin, String key) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserLogin validateUser(UserLogin userLogin, String key) throws Exception {
        Optional<CurrentUser> opt = currentUserRepo.findByUuid(key) ;
        if(opt.isEmpty()) {
            throw new Exception("Invalid Key");
        }
        CurrentUser currentUser = opt.get();
        Optional<UserRegistration> currentCustomerOpt = userRegistrationRepo.findById(currentUser.getCustomerId()) ;
        UserRegistration userRegistration= currentCustomerOpt.get();
        if(userLogin.getUserId().equals(currentUser.getCustomerId()) && userLogin.getPassword().equals(userRegistration.getPassword())) {
            return userLogin;
        }
        else {
            throw new Exception("Invalid Mobile Number or Password");
        }
    }

    @Override
    public String signOut(String key) throws Exception {
        CurrentUser userSession = currentUserService.getCurrentCustomerSession(key);
        if(userSession != null) {
            currentUserRepo.delete(userSession);
            return "Logged out...";
        }
        else {
            throw new Exception("Having some problem to logout");
        }
    }
}
