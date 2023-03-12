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
    public RegisterUser addCustomer(RegisterUser cust) throws CustomerException {
       Optional<RegisterUser> opt = userRepo.findByUsername(cust.getUsername()) ;
        if(opt.isPresent()) {
            throw new CustomerException("RegisterUser already Exist With this Username");
        }

        return userRepo.save(cust);
    }

    @Override
    public RegisterUser updateCustomer(RegisterUser cust, String key) throws CustomerException, LoginException {

        RegisterUser registerUserDetails = currentCustomerService.getCustomerDetails(key) ;

        if(registerUserDetails == null) {
            throw new LoginException("No user Found | Login first");
        }else if( cust.getMobile_number().toCharArray().length != 10 ){

            throw new CustomerException("Mobile Number can only be of 10 digit");
        }

        if(cust.getCustomerId() == registerUserDetails.getCustomerId()) {
            return userRepo.save(cust) ;
        }
        else {
            throw new CustomerException("Can't change UserID!") ;
        }


    }
    @Override
    public RegisterUser getCustomerDetailsByUsername(String username) throws Exception {
        return userRepo.findByUsername(username)
                .orElseThrow(
                        () -> new Exception("RegisterUser not found with username: " + username)
                );
    }

    @Override
    public RegisterUser removeCustomer(RegisterUser cust, String key) throws CustomerException, LoginException {

//		Optional<RegisterUser> opt = customerDao.findById(cust.getCustomerId());
//
//		if(opt.isEmpty()) {
//			throw new CustomerException("RegisterUser is not registered");
//		}

//        RegisterUser currentCustomer = currentUserSessionService.getCustomerDetails(key);
//
//        if(currentCustomer != null) {
//
//            if(cust.getCustomerId() == currentCustomer.getCustomerId()) {
//
//                customerRepo.delete(cust);
//
//                Optional<CurrentUserSession> opt = currentUserSessionDao.findByUuid(key) ;
//
//                CurrentUserSession currentSession = opt.get();
//
//                currentUserSessionDao.delete(currentSession);
//                return cust;
//
//
//            }
//            else {
//                throw new CustomerException("Invalid RegisterUser ID") ;
//            }
//
//        }
//        else {
//            throw new CustomerException("Invalid UUID key");
//        }
//


        return null;
    }

    @Override
    public RegisterUser viewCustomer(Integer customerId) throws CustomerException {
        Optional<RegisterUser> cust = userRepo.findById(customerId);
        cust.orElseThrow(()-> new CustomerException("RegisterUser doesn't found..."));
        return cust.get();

    }

    @Override
    public List<RegisterUser> viewAllCustomer() throws CustomerException {
        return userRepo.findAll();
    }

}
