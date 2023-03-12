package com.example.attikometroapi.Service;

import com.example.attikometroapi.Exceptions.CustomerException;
import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Model.RegisterUser;

import java.util.List;

public interface UserService {

    public RegisterUser addCustomer(RegisterUser cust) throws CustomerException;

    public RegisterUser updateCustomer(RegisterUser cust, String key) throws CustomerException, LoginException ;

    public RegisterUser removeCustomer(RegisterUser cust, String key) throws CustomerException, LoginException ;

    public RegisterUser viewCustomer(Integer customerId)  throws CustomerException;

    public List<RegisterUser> viewAllCustomer() throws CustomerException ;

    public RegisterUser getCustomerDetailsByUsername(String username) throws Exception;
}
