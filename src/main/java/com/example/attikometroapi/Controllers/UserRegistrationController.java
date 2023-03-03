package com.example.attikometroapi.Controllers;

import com.example.attikometroapi.Model.UserRegistration;
import com.example.attikometroapi.Service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping("/users")
    public ResponseEntity<UserRegistration> addUserHandler(@Valid @RequestBody UserRegistration userRegistration) throws Exception{
        UserRegistration addedCustomer = userRegistrationService.addUser(userRegistration);
        return new ResponseEntity<UserRegistration>(addedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/users/{key}")
    public ResponseEntity<UserRegistration> updateUserHandler(@PathVariable("key") String key,@RequestBody UserRegistration userRegistration) throws Exception{
        UserRegistration updatedCustomer = userRegistrationService.updateUser(userRegistration, key);
        return new ResponseEntity<UserRegistration>(updatedCustomer, HttpStatus.CREATED);
    }


    @GetMapping("/users/{userid}")
    public ResponseEntity<UserRegistration> getUserHandler(@PathVariable("customerid") Integer userId) throws Exception{
        UserRegistration existingCustomer = userRegistrationService.viewUser(userId);
        return new ResponseEntity<UserRegistration>(existingCustomer, HttpStatus.OK);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<UserRegistration>> getAllUser() throws Exception {
        List<UserRegistration> customers = userRegistrationService.viewAllUser();
        return new ResponseEntity<List<UserRegistration>>(customers, HttpStatus.OK);
    }

}
