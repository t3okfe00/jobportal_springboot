package com.feodev.jobportal.controller;

import com.feodev.jobportal.entity.Users;
import com.feodev.jobportal.entity.UsersType;
import com.feodev.jobportal.services.UsersService;
import com.feodev.jobportal.services.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {
    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    public UsersController(UsersTypeService usersTypeService,UsersService usersService){
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> usersTypes=usersTypeService.getAll();
        model.addAttribute("getAllTypes",usersTypes);
        model.addAttribute("user",new Users());
        return "register";

    }


    @PostMapping("/register/new")
    public String userRegistration(@Valid Users user,Model model){
        System.out.println("User ::"+ user);

        //First check if the email already exist in the database
        Optional<Users> existingUser = usersService.getByEmail(user.getEmail());
        if(existingUser.isPresent()){
            model.addAttribute("error","Email already registered");
            List<UsersType> usersTypes=usersTypeService.getAll();
            model.addAttribute("getAllTypes",usersTypes);
            model.addAttribute("user",new Users());
            return "register";
        }


        // Registering the suer
        usersService.addNew(user);
        return "dashboard";
    }

}
