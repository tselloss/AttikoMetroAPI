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


    @PostMapping("/customers")
    public ResponseEntity<RegisterUser> addCustomerHandler(@Valid @RequestBody RegisterUser registerUser) throws CustomerException{
        RegisterUser addedRegisterUser = userService.addCustomer(registerUser);
       // String message = "Dear "+registerUser.getUsername() +",\n\nThank you for registering with our online cinema movie booking application. We look forward to bringing you the best movie experience.\n\nSincerely,\nThe Online Cinema Movie Booking Shop team";
      //  emailSenderService.sendEmail(registerUser.getEmail(),"Registry confirmation",message);
        return new ResponseEntity<RegisterUser>(addedRegisterUser, HttpStatus.CREATED);
    }

    @PutMapping("/customers/{key}")
    public ResponseEntity<RegisterUser> updateCustomerHandler(@PathVariable("key") String key, @RequestBody RegisterUser registerUser) throws LoginException, CustomerException{
        RegisterUser updatedRegisterUser = userService.updateCustomer(registerUser, key);
        return new ResponseEntity<RegisterUser>(updatedRegisterUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{key}")
    public ResponseEntity<RegisterUser> removeCustomerHandler(@PathVariable("key") String key, @RequestBody RegisterUser registerUser) throws CustomerException, LoginException{
        RegisterUser deletedRegisterUser = userService.removeCustomer(registerUser, key);
        return new ResponseEntity<RegisterUser>(deletedRegisterUser, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerid}")
    public ResponseEntity<RegisterUser> getCustomerHandler(@PathVariable("customerid") Integer customerId) throws CustomerException{
        RegisterUser existingRegisterUser = userService.viewCustomer(customerId);
        return new ResponseEntity<RegisterUser>(existingRegisterUser, HttpStatus.OK);
    }

    @GetMapping("/getallcustomers")
    public ResponseEntity<List<RegisterUser>> getAllCustomers() throws CustomerException {
        List<RegisterUser> registerUsers = userService.viewAllCustomer();
        return new ResponseEntity<List<RegisterUser>>(registerUsers, HttpStatus.OK);
    }
}
