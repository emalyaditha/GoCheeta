package com.example.GoCheeta.controller;

import com.example.GoCheeta.model.Customer;
import com.example.GoCheeta.repository.CustomerRepository;
import com.example.GoCheeta.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class CustomerController {


    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository userRepo;

    @GetMapping("")
    public String showHomepage() {
        return "index";
    }

    @GetMapping("/register")
    public String showNewForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Register Form.");
        return "registerUser";
    }

    @PostMapping("/register_user")
    public String regiUser(Customer customer, RedirectAttributes ra) {
        service.save(customer);
        ra.addFlashAttribute("message", "Registered Succesfully");
        return "redirect:";
    }
    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("customer") Customer user, RedirectAttributes ra) {

        Customer oauthUser = service.log(user.getEmail(),user.getPassword());

        System.out.println(oauthUser);
        if(Objects.nonNull(oauthUser))
        {
            ra.addFlashAttribute("message", "You have successfully logged in");
            return "redirect:";
        } else {
            ra.addFlashAttribute("message", "Please provide the corret email and password");
            return "redirect:/login";
        }
    }

    @GetMapping("/adminManageCustomer")
    public String showCustomerList(Model model) {
        List<Customer> listCustomer = service.listAll();
        model.addAttribute("listCustomer", listCustomer);

        return "adminManageCustomer";
    }

    @GetMapping("/Customer/edit/{id}")
    public String customerEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes rs) {
        try {
            Customer customer = service.get(id);
            model.addAttribute("customer", customer);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "adminUpdateCustomer";
        } catch (UserNotFoundException e) {
           rs.addFlashAttribute("message", "The Customer has been updated successfully");

        }

        return "redirect:/adminManageCustomer";
    }

    @GetMapping("/Customer/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes rs) {
        try {
            service.delete(id);
            rs.addFlashAttribute("message", "The Customer ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/adminManageCustomer";
    }

    @PostMapping("/adminUpdateCustomer")
    public String updateCustomer(Customer customer, RedirectAttributes ra) {
        service.save(customer);
        ra.addFlashAttribute("message", "The Customer has been updated successfully");
        return "redirect:adminManageCustomer";
    }
}
