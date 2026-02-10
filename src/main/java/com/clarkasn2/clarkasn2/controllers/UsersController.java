package com.clarkasn2.clarkasn2.controllers;

//Author: Clark Noveros
//For: CMPT276 Asn2

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    //-->DETAILS PAGE<--

    //link to details
    @GetMapping("users/details/{uid}")
    public String detailsUser(@PathVariable int uid, Model model, RedirectAttributes redirectAttributes){
        Users user = usersList.stream().filter(u->u.getUid() == uid).findFirst().orElse(null);

        if (user == null){
            redirectAttributes.addFlashAttribute("error", "User not in database");
            return "redirect:/users/index";
        }

        model.addAttribute("user",user);
        return "users/details";
    }
    
    //delete
    @GetMapping("users/delete/{uid}")
    public String deleteUser(@PathVariable int uid, RedirectAttributes redirectAttributes) {
        boolean removed = usersList.removeIf(u -> u.getUid() == uid);

        if(removed){
            redirectAttributes.addFlashAttribute("success", "User has been deleted!");
        }
        else{
            redirectAttributes.addFlashAttribute("error", "User not in database.");
        }

        return "redirect:/users/index";
    }

    
    //-->CREATE PAGE<--
    
    //show create form, link to create page
    @GetMapping("users/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new Users());

        return "users/create";
    }
    
    //submit create form 
    @PostMapping("users/create")
    public String submitCreateForm(@ModelAttribute Users user, RedirectAttributes redirectAttributes){

        user.setUid(usersList.size() + 1);
        usersList.add(user);

        redirectAttributes.addFlashAttribute("success", "User created!");

        return "redirect:/users/details" + user.getUid();
    }

    
    //-->EDIT PAGE<--
    //show edit form, link to edit page
    @GetMapping("users/edit/{uid}")
    public String showEditForm(@PathVariable int uid, Model model, RedirectAttributes redirectAttributes) {
        
        Users user = usersList.stream().filter(u -> u.getUid() == uid).findFirst().orElse(null);
        
        if (user == null){

            redirectAttributes.addFlashAttribute("error", "User not in database.");
            return "redirect:/users/index";
        }

        model.addAttribute("user",user);
        return "users/edit";
    }

    //submit edit form 
    @PostMapping("users/edit/{uid}")
    public String submitEditForm(@PathVariable int uid, @ModelAttribute Users updatedUser, RedirectAttributes redirectAttributes) {
        
        Users user = usersList.stream().filter(u -> u.getUid() == uid).findFirst().orElse(null);

        if(user == null){
            redirectAttributes.addFlashAttribute("error", "User not in database.");
            return "redirect:/users/index";
        }

        //edit new data
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setRoleType(updatedUser.getRoleType());
        user.setClarity(updatedUser.getClarity());
        user.setNiceness(updatedUser.getNiceness());
        user.setKnowledgeableScore(updatedUser.getKnowledgeableScore());
        user.setComment(updatedUser.getComment());

        redirectAttributes.addFlashAttribute("success", "Updated successfully.");

        return "redirect:/users/details" + uid;
    }



}
    
    
