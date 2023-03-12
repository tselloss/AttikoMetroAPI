package com.example.attikometroapi.Service;

import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Model.CurrentUserSession;
import com.example.attikometroapi.Model.RegisterUser;

public interface CurrentCustomerService {
    public CurrentUserSession getCurrentUserSession(String key) throws LoginException;
    public Integer getCurrentUserId(String key) throws LoginException;
    public RegisterUser getUserDetails(String key) throws LoginException;
}


