/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import java.util.Map;
import models.Product;
import models.ProductJDBCTemplate;
import models.Sale;
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
public class ProductsController {
    @Autowired
    private ApplicationContext context;
    @RequestMapping(value = "/backend/Products/new", method = RequestMethod.GET)
    public String actionNewProduct(ModelMap model){
        Product product = new Product();
        model.addAttribute("productForm",product);
        model.addAttribute("pageTitle", "Create New Product");
        
        return "/Products/edit";
    }
    
    /**
     *
     * @param product
     * @param model
     * @return
     */
    @RequestMapping(value = "/backend/Products/manage", method = RequestMethod.POST)
    public String actionManageProduct
        (
            @ModelAttribute("productForm")Product product,
            ModelMap model
        ){
        ProductJDBCTemplate productDB = (ProductJDBCTemplate) context.getBean("productJDBCTemplate");
        if(product.getId() == 0){
             productDB.create(product.getName(), product.getDescription(), product.getPrice());
             model.addAttribute("pageTitle", "Back From Controller");
             return "redirect:/backend/Products/index";
        }else{
            productDB.update(product.getId(), product.getName(), product.getDescription(), product.getPrice());
            return "redirect:/backend/Products/edit/"+product.getId();
        }
       

    }
        
    @RequestMapping(value="/Products/show/{productId}", method = RequestMethod.GET)
        public String actionShowProduct(@PathVariable("productId")int productId, ModelMap model){
            ProductJDBCTemplate productDB = (ProductJDBCTemplate) context.getBean("productJDBCTemplate");
            Product product = productDB.find(productId);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Details about: "+product.getName());
            
            return "/Products/show";
        }
    @RequestMapping(value="/backend/Products/index", method = RequestMethod.GET)
    public String actionProductsIndexBe(ModelMap model){
        ProductJDBCTemplate productDB = (ProductJDBCTemplate) context.getBean("productJDBCTemplate");
        List<Product> products = productDB.all();
        model.addAttribute("products", products);
        model.addAttribute("pageTitle", "View All Products");
        
        return "/Products/index";
    }
    
    @RequestMapping(value = "/backend/Products/edit/{productId}", method = RequestMethod.GET)
    public String actionProductsEdit
        (
                @PathVariable("productId")int productId,
                ModelMap model
        )
        {
            ProductJDBCTemplate productDB = (ProductJDBCTemplate) context.getBean("productJDBCTemplate");
            Product product = productDB.find(productId);
            model.addAttribute("productForm",product);
            model.addAttribute("pageTitle","Edit product "+product.getName());
            
            return "/Products/edit";
        }
        
    @RequestMapping(value = "/backend/Products/delete/{prodId}", method = RequestMethod.GET)
    public String actionProductsDelete(@PathVariable("prodId")int prodId){
        ProductJDBCTemplate productDB = (ProductJDBCTemplate) context.getBean("productJDBCTemplate");
        productDB.delete(prodId);
        
        return "redirect:/backend/Products/index";
    }
    
    @RequestMapping(value = "Products/buy/{prodId}/user/{userId}", method = RequestMethod.GET)
    public String actionBuy(@PathVariable("prodId")int prodId, @PathVariable("userId")int userId){
        UserJDBCTemplate userDB = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
        userDB.buyProduct(userId, prodId);
        return"redirect:/home";
    }
    
    @RequestMapping(value="/backend/ManualSale", method = RequestMethod.GET)
    public String actionManualSale(ModelMap model){
        ProductJDBCTemplate productDB = (ProductJDBCTemplate) context.getBean("productJDBCTemplate");
        UserJDBCTemplate userDB = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
        Map<Integer, String> productsList = productDB.listAll();
        Map<Integer, String> usersList = userDB.listAll();
        
        model.addAttribute("clients", usersList);
        model.addAttribute("products", productsList);
        model.addAttribute("pageTitle", "Create Manual Sale");
        Sale sale = new Sale();
        model.addAttribute("saleForm", sale);
        
        return "staticPages/manualSale";
    }
}
