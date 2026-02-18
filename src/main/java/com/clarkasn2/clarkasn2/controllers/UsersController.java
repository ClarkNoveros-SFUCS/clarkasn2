package com.clarkasn2.clarkasn2.controllers;

//Author: Clark Noveros
//For: CMPT276 Asn2

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clarkasn2.clarkasn2.models.UserRepository;
import com.clarkasn2.clarkasn2.models.Users;

import jakarta.validation.Valid;






//users: passing a collection (list)
//user: passing one object

@Controller
public class UsersController {

    @Autowired 
    private UserRepository userRepo;

    //-->INDEX PAGE<--
    @GetMapping("/users/index")
    public String getAllUsers(Model model){
        List<Users> users = userRepo.findAll();
        model.addAttribute("users",users);
        return "users/index";
    }


    //-->DETAILS PAGE<--

    //link to details
    @GetMapping("/users/details/{uid}")
    public String detailsUser(@PathVariable int uid, Model model, RedirectAttributes redirectAttributes){
        Users user = userRepo.findById(uid).orElse(null);

        if (user == null){
            redirectAttributes.addFlashAttribute("error", "User not in database");
            return "redirect:/users/index";
        }

        model.addAttribute("user",user);
        return "users/details";
    }
    
    //delete
    @PostMapping("/users/delete/{uid}")
    public String deleteUser(@PathVariable int uid, RedirectAttributes redirectAttributes) {
        if(!userRepo.existsById(uid)){
            redirectAttributes.addFlashAttribute("error", "User not in database");
            return "redirect:/users/index";
        }

        userRepo.deleteById(uid);
        redirectAttributes.addFlashAttribute("success", "User has been deleted");

        return "redirect:/users/index";
    }

    
    //-->CREATE PAGE<--
    
    //show create form, link to create page
    @GetMapping("/users/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new Users());

        return "users/create";
    }
    
    //submit create form 
    @PostMapping("/users/create")
    public String submitCreateForm(@Valid @ModelAttribute("user") Users user, BindingResult result, RedirectAttributes redirectAttributes){

        if (result.hasErrors()){
            return "users/create";
        }
       
        userRepo.save(user);
        redirectAttributes.addFlashAttribute("success", "User created!");
        return "redirect:/users/details/" + user.getUid();
    }

    
    //-->EDIT PAGE<--
    //show edit form, link to edit page
    @GetMapping("/users/edit/{uid}")
    public String showEditForm(@PathVariable int uid, Model model, RedirectAttributes redirectAttributes) {
        
        Users user = userRepo.findById(uid).orElse(null);
        
        if (user == null){

            redirectAttributes.addFlashAttribute("error", "User not in database.");
            return "redirect:/users/index";
        }

        model.addAttribute("user",user);
        return "users/edit";
    }

    //submit edit form 
    @PostMapping("/users/edit/{uid}")
    public String submitEditForm(@PathVariable int uid, @Valid @ModelAttribute("user") Users updatedUser, BindingResult result, RedirectAttributes redirectAttributes) {
        
        if(result.hasErrors()){
            return "users/edit";
        }

        if(!userRepo.existsById(uid)){
            redirectAttributes.addFlashAttribute("error", "User not in database.");
            return "redirect:/users/index";
        }

        updatedUser.setUid(uid);
        userRepo.save(updatedUser);

        redirectAttributes.addFlashAttribute("success", "Updated successfully.");

        return "redirect:/users/details/" + uid;
    }



}
    
    
