package com.example.attikometroapi.Service;

import com.example.attikometroapi.Exceptions.CustomerException;
import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Model.RegisterUser;

import java.util.List;

public interface UserService {

    public RegisterUser addUser(RegisterUser registerUser) throws CustomerException;

    public RegisterUser updateUser(RegisterUser registerUser, String key) throws CustomerException, LoginException ;

    public RegisterUser removeUser(RegisterUser registerUser, String key) throws CustomerException, LoginException ;

    public RegisterUser viewUser(Integer userId)  throws CustomerException;

    public List<RegisterUser> viewAllUser() throws CustomerException ;

    public RegisterUser getUserDetailsByUsername(String username) throws Exception;
}
