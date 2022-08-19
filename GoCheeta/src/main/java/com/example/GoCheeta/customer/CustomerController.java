package com.example.GoCheeta.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
public class CustomerController {


    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository userRepo;

    @GetMapping("")
    public String showhomepage() {
        return "index";
    }

    @GetMapping("/register")
    public String showNewForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register_user";
    }

    @PostMapping("/register_user")
    public String regiUser(Customer customer, RedirectAttributes ra) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(customer.getPassword());
//        customer.setPassword(encodedPassword);
        service.save(customer);
        ra.addFlashAttribute("message", "You have been Registered Succesfully");
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
            return "login";
        }
    }


//    @RequestMapping(value = "/admin", method = RequestMethod.POST)
//    public String admin(ModelMap model, @RequestParam String email, @RequestParam String password, RedirectAttributes ra) {
//        if (email.equals("emalyaditha@gmail.com") && password.equals("12345")) {
//            ra.addFlashAttribute("message", "You have successfully logged in");
//            return "redirect:/index";
//        }
//        ra.addFlashAttribute("message", "Please provide the corret email and password");
//        return "redirect:/login";
//    }
}
