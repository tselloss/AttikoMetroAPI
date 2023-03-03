package com.example.attikometroapi.Service;

import com.example.attikometroapi.Model.UserRegistration;

import java.util.List;

public interface UserRegistrationService {
    public UserRegistration addCustomer(UserRegistration userRegistration) throws Exception;

    public UserRegistration updateCustomer(UserRegistration userRegistration, String key) throws Exception ;

    public UserRegistration removeCustomer(UserRegistration userRegistration, String key) throws Exception ;

    public UserRegistration viewCustomer(Integer customerId)  throws Exception;

    public List<UserRegistration> viewAllCustomer() throws Exception ;

    public UserRegistration getCustomerDetailsByUsername(String username) throws Exception;
}
