package com.clarkasn2.clarkasn2.controllers;

//Author: Clark Noveros
//For: CMPT276 Asn2

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clarkasn2.clarkasn2.models.Users;

//users: passing a collection (list)
//user: passing one object

@Controller
public class UsersController {

    private List<Users> usersList = new ArrayList<>();

    //-->INDEX PAGE<--
    @GetMapping("users/index")
    public String getAllUsers(Model model){
        model.addAttribute("users",usersList);
        return "users/index";
    }

    //link to create 
    @GetMapping("users/create")
    public String createButton(Model model) {
        model.addAttribute("user", new Users());
        return "users/create";
    }

    //link to details
    @GetMapping("users/details/{uid}")
    public String detailsUser(@PathVariable("uid") int uid, Model model){
        Users user = usersList.stream().filter(u->u.getUid() == uid).findFirst().orElse(null);

        if (user == null) 
        return "redirect:/users/index";

        model.addAttribute("user",user);
        return "users/details";
    }

    //DETAILS PAGE

    //CREATE PAGE


    //EDIT PAGE



}
    
    
