package com.EMedConf.demo.Controller;

import com.EMedConf.demo.Model.User;
import com.EMedConf.demo.Service.Implementation.UserServiceImplementation;
import com.EMedConf.demo.Utility.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
public class EMedConfController {

    @Autowired
    UserServiceImplementation userServiceImplementation;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/home")
    public String Home(){
        return "Welcome home !!";
    }

    @PostMapping("/admin")
    public String admin(){
        return "Welcome admin !!!";
    }

    @GetMapping("/")
    public String Hello(){
        return   "Hello , Welcome  !!!";
    }

    @PostMapping("/register/patient")
    public String Register(@RequestBody User user){

        if(userServiceImplementation.ExistsByUsername(user.getUsername()) != null) {
            return "register failed \n the user already exists !!!!!";
        }

User Newuser = new User();
        Newuser.setUsername(user.getUsername());
        Newuser.setPassword(passwordEncoder.encode((user.getPassword())));
        Newuser.setEmail(user.getEmail());
        Newuser.setRole(Role.PATIENT);
  userServiceImplementation.CreateUser(Newuser);

        return "register succeed  !!!";
    }

    @PostMapping("/patient")
    public String patient(){
        return "Welcome patient !!!";
    }

    @PostMapping("/register/medecin")
    public String RegisterMedecin(@RequestBody User user){

        if(userServiceImplementation.ExistsByUsername(user.getUsername()) != null) {
            return "register failed \n the user already exists !!!!!";
        }

        User Newuser = new User();
        Newuser.setUsername(user.getUsername());
        Newuser.setPassword(passwordEncoder.encode((user.getPassword())));
        Newuser.setEmail(user.getEmail());
        Newuser.setRole(Role.MEDECIN);
        userServiceImplementation.CreateUser(Newuser);

        return "register succeed  !!!";
    }



}
