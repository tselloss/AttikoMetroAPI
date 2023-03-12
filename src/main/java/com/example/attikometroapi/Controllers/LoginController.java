package com.example.attikometroapi.Controllers;

import com.example.attikometroapi.DTO.LoginDTO;
import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Exceptions.UserException;
import com.example.attikometroapi.Model.CurrentUserSession;
import com.example.attikometroapi.Model.RegisterUser;
import com.example.attikometroapi.Repository.CurrentUserSessionRepo;
import com.example.attikometroapi.Service.UserService;
import com.example.attikometroapi.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;
    @Autowired
    private CurrentUserSessionRepo currentUserSessionRepo;

    @PostMapping("/login")
    public ResponseEntity addUser(@RequestBody LoginDTO loginDTO) throws Exception {
        Map<String, String> model = new HashMap<>();
        RegisterUser savedRegisterUser = userService.getUserDetailsByUsername(loginDTO.getUsername());
        if (!savedRegisterUser.getPassword().equals(loginDTO.getPassword())) {
            throw new Exception("Invalid username/password");
        }
        model.put("message","Logged in Successfully");
        model.put("token", savedRegisterUser.getUsername());
        model.put("customer_id", String.valueOf(savedRegisterUser.getUserId()));
        LocalDateTime rightNow = LocalDateTime.now();
        CurrentUserSession currentUserSession= new CurrentUserSession(loginDTO.getUser_id(), loginDTO.getUsername(),rightNow );
        currentUserSessionRepo.save(currentUserSession);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestParam String key) throws UserException, LoginException {
        String response = loginService.signOut(key);
        return new ResponseEntity<String>(response,HttpStatus.OK) ;
    }
}
