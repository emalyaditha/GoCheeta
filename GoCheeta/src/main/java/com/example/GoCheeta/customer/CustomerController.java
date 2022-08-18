package com.example.GoCheeta.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository userRepo;

    @GetMapping("register_user")
    public String showNewForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register_user";
    }

    @PostMapping("/register_user")
    public String regiUser(Customer customer, RedirectAttributes rs) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(customer.getPassword());
//        customer.setPassword(encodedPassword);
       // userRepo.save(customer);
        rs.addFlashAttribute("message", "You have been Registered Succesfully");
        return "index";
    }
}
