package com.example.attikometroapi.Service;

import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Model.CurrentUserSession;
import com.example.attikometroapi.Model.RegisterUser;

public interface CurrentCustomerService {
    public CurrentUserSession getCurrentCustomerSession(String key) throws LoginException;
    public Integer getCurrentCustomerId(String key) throws LoginException;
    public RegisterUser getCustomerDetails(String key) throws LoginException;
}


