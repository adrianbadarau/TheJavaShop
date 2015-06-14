/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.Product;
import models.ProductJDBCTemplate;
import models.Sale;
import models.User;
import models.UserJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author adrian
 */
@Controller
public class StaticPagesController {
    @Autowired
    private ApplicationContext context;
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHomePage(ModelMap model){
        model.addAttribute("pageTitle", "The JavaStore HomePage");
        ProductJDBCTemplate productDB = (ProductJDBCTemplate) context.getBean("productJDBCTemplate");
        List<Product> products = productDB.all();
        model.addAttribute("products",products);
        return "/staticPages/home";
    }
    
    @RequestMapping(value="/backend/", method=RequestMethod.GET)
    public String actionBackend(ModelMap model){
        model.addAttribute("pageTitle", "Welcome to the backend");
        UserJDBCTemplate userDB = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
        List<Sale> sold = userDB.getSoldProducts();
        model.addAttribute("sold",sold);
        
        return "/staticPages/backend";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String actionCheckUser(HttpServletRequest request){
        if(request.getSession().getAttribute("userId") != null){
            return "redirect:/home";
        }else{
            return "redirect:/login";
        }
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String actionLogin(ModelMap model){
        model.addAttribute("pageTitle", "Registration Page");        
        return "staticPages/login";
    }
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String adctionAuthenticate(HttpServletRequest request, ModelMap model){
        
        try {
           UserJDBCTemplate userDB = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
           User user = userDB.findBy("Name", request.getParameter("userName"));
           if(user.getPass().equals(request.getParameter("userPass"))){
               request.getSession().setAttribute("userId", user.getId());
               request.getSession().setAttribute("userName", user.getName());
               if(user.getName().equals("admin")){
                   request.getSession().setAttribute("isAdmin", "true");
               }else{
                   request.getSession().setAttribute("isAdmin", "false");
               }
               return "redirect:/home";
           }else{
               model.addAttribute("error","Sory wrong pass");
           }
        } catch (Exception e) {
            model.addAttribute("error", "Srooy wrong username or db is down");
        }
        return "redirect:/login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String actionLogout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }
    
//    @RequestMapping(value = "/startApplication/realy/{value}", method = RequestMethod.GET)
//    public String actionStartApplication(@PathVariable("value")boolean realy){
//        if(realy){
//            UserJDBCTemplate userDB = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
//            userDB.create("admin", "admin");
//            System.out.println("Working");
//        }
//            return "redirect:/";
//    }
}
