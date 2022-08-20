package com.example.GoCheeta.admin;

import com.example.GoCheeta.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @Autowired
    private AdminService service;

    @Autowired
    private AdminRepository adminRepo;

    @GetMapping("/adminLogin")
    public String showNewForm() {

        return "adminLogin";
    }

    @GetMapping("/adminHome")
    public String showAHomeForm() {

        return "adminHome";
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String admin(ModelMap model, @RequestParam String email, @RequestParam String password, RedirectAttributes ra) {
        if (email.equals("Admin") && password.equals("root")) {
            ra.addFlashAttribute("message", "You have successfully logged in");
            return "redirect:/adminHome";
        }
            ra.addFlashAttribute("message", "Please provide the corret email and password");
            return "redirect:/adminLogin";
    }
}
