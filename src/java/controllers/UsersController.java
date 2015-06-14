/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import models.User;
import models.UserJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author adrian
 */

@Controller
public class UsersController {
    @Autowired
    private ApplicationContext context;
    @RequestMapping(value = "/backend/Users/manage", method = RequestMethod.POST)
    public String actionRegister(@ModelAttribute("userForm")User user, ModelMap model){
        UserJDBCTemplate userDB = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
        if(user.getId() == 0){
            userDB.create(user.getName(), user.getPass());            
        }else{
            userDB.update(user.getId(), user.getName(), user.getPass());
        }
        return "redirect:/backend/Users/index";
    }
    
    @RequestMapping(value = "/backend/Users/index", method = RequestMethod.GET)
    public String actionUsersIndex(ModelMap model){
        UserJDBCTemplate userDB = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
        List<User> users = userDB.all();
        model.addAttribute("users", users);
        model.addAttribute("pageTitle","Users Index");
        
        return "/Clients/index";
    }
    
    @RequestMapping(value = "/backend/Users/new", method = RequestMethod.GET)
    public String actionNewUser(ModelMap model){
        User user = new User();
        model.addAttribute("userForm", user);
        model.addAttribute("pageTitle", "Create new user");
        
        return "/Clients/edit";
    }
    
    @RequestMapping(value = "/backend/Users/edit/{userId}", method = RequestMethod.GET)
    public String actionEditUser(@PathVariable("userId")int userId, ModelMap model){
        UserJDBCTemplate userDB = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
        User user = userDB.find(userId);
        model.addAttribute("userForm", user);
        model.addAttribute("pageTitle", "Edit User"+user.getName());
        
        return "/Clients/edit";
    }
    
    @RequestMapping(value = "/backend/Users/delete/{userId}", method = RequestMethod.GET)
    public String actionDeleteUser(@PathVariable("userId")int userId){
        UserJDBCTemplate userDB = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
        userDB.delete(userId);        
        return "redirect:/backend/Users/index";
    }
    
}
