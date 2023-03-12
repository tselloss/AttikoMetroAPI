package com.example.attikometroapi.Service;

import com.example.attikometroapi.Exceptions.CustomerException;
import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Exceptions.UserException;
import com.example.attikometroapi.Model.CurrentUserSession;
import com.example.attikometroapi.Model.User;

public interface LoginService{
        public CurrentUserSession addUser(User user) throws UserException, CustomerException;
        public User removeUser(User user, String key) throws UserException ;
        public User validateUser(User user,String key) throws UserException, LoginException;
        public String signOut(String key) throws UserException, LoginException ;
        }
