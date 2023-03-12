package com.example.attikometroapi.Controllers;

import com.example.attikometroapi.Exceptions.CustomerException;
import com.example.attikometroapi.Exceptions.LoginException;
import com.example.attikometroapi.Model.RegisterUser;
import com.example.attikometroapi.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/users")
    public ResponseEntity<RegisterUser> addUserHandler(@Valid @RequestBody RegisterUser registerUser) throws CustomerException{
        RegisterUser addedRegisterUser = userService.addUser(registerUser);
       // String message = "Dear "+registerUser.getUsername() +",\n\nThank you for registering with our online cinema movie booking application. We look forward to bringing you the best movie experience.\n\nSincerely,\nThe Online Cinema Movie Booking Shop team";
      //  emailSenderService.sendEmail(registerUser.getEmail(),"Registry confirmation",message);
        return new ResponseEntity<RegisterUser>(addedRegisterUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{key}")
    public ResponseEntity<RegisterUser> updateUserHandler(@PathVariable("key") String key, @RequestBody RegisterUser registerUser) throws LoginException, CustomerException{
        RegisterUser updatedRegisterUser = userService.updateUser(registerUser, key);
        return new ResponseEntity<RegisterUser>(updatedRegisterUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{key}")
    public ResponseEntity<RegisterUser> removeUserHandler(@PathVariable("key") String key, @RequestBody RegisterUser registerUser) throws CustomerException, LoginException{
        RegisterUser deletedRegisterUser = userService.removeUser(registerUser, key);
        return new ResponseEntity<RegisterUser>(deletedRegisterUser, HttpStatus.OK);
    }

    @GetMapping("/users/{userid}")
    public ResponseEntity<RegisterUser> getUserHandler(@PathVariable("user_id") Integer userId) throws CustomerException{
        RegisterUser existingRegisterUser = userService.viewUser(userId);
        return new ResponseEntity<RegisterUser>(existingRegisterUser, HttpStatus.OK);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<RegisterUser>> getAllUser() throws CustomerException {
        List<RegisterUser> registerUsers = userService.viewAllUser();
        return new ResponseEntity<List<RegisterUser>>(registerUsers, HttpStatus.OK);
    }
}
