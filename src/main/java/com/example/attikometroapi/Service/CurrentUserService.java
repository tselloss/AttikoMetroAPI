package com.example.attikometroapi.Service;

import com.example.attikometroapi.Model.CurrentUser;
import com.example.attikometroapi.Model.UserRegistration;

public interface CurrentUserService {
    public CurrentUser getCurrentCustomerSession(String key) throws Exception;
    public Integer getCurrentCustomerId(String key) throws Exception;
    public UserRegistration getCustomerDetails(String key) throws Exception;
}
