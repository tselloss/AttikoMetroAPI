package com.example.attikometroapi.Service;

import com.example.attikometroapi.Model.CurrentUser;
import com.example.attikometroapi.Model.UserLogin;

public interface UserLoginService {
    public CurrentUser addUser(UserLogin userLogin) throws Exception;
    public UserLogin removeUser(UserLogin userLogin, String key) throws Exception ;
    public UserLogin validateUser(UserLogin userLogin, String key) throws Exception;
    public String signOut(String key) throws Exception ;
}
