package com.example.attikometroapi.Service;

import com.example.attikometroapi.Model.UserRegistration;

import java.util.List;

public interface UserRegistrationService {
    public UserRegistration addUser(UserRegistration userRegistration) throws Exception;

    public UserRegistration updateUser(UserRegistration userRegistration, String key) throws Exception ;

    public UserRegistration removeUser(UserRegistration userRegistration, String key) throws Exception ;

    public UserRegistration viewUser(Integer userId)  throws Exception;

    public List<UserRegistration> viewAllUser() throws Exception ;


    public UserRegistration getUserDetailsByUsername(String username) throws Exception;
}
