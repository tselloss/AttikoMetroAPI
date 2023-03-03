package com.example.attikometroapi.Controllers;

import com.example.attikometroapi.DTO.LoginDTO;
import com.example.attikometroapi.Model.UserRegistration;
import com.example.attikometroapi.Repository.UserRegistrationRepo;
import com.example.attikometroapi.Service.UserLoginService;
import com.example.attikometroapi.Service.UserRegistrationService;
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
    private UserLoginService loginService;

    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private UserRegistrationRepo userRegistrationRepo;

    @PostMapping("/login")
    public ResponseEntity addUser(@RequestBody LoginDTO loginDTO) throws Exception {
        Map<String, String> model = new HashMap<>();
        UserRegistration savedUser = userRegistrationService.getUserDetailsByUsername(loginDTO.getUsername());
        if (!savedUser.getPassword().equals(loginDTO.getPassword())) {
            throw new Exception("Invalid username/password");
        }
        model.put("message","Logged in Successfully");
        model.put("token",savedUser.getUsername());
        model.put("customer_id", String.valueOf(savedUser.getUserId()));
        LocalDateTime rightNow = LocalDateTime.now();
        UserRegistration currentUserSession= new UserRegistration(loginDTO.getCustomer_id(), loginDTO.getUsername(),rightNow );
        userRegistrationRepo.save(currentUserSession);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestParam String key) throws Exception {
        String response = loginService.signOut(key);
        return new ResponseEntity<String>(response,HttpStatus.OK) ;
    }

}
