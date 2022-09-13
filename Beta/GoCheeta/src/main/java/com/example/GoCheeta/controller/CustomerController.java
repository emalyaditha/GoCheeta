package com.example.GoCheeta.controller;
import com.example.GoCheeta.model.Booking;
import com.example.GoCheeta.model.Users;
import com.example.GoCheeta.repository.BookingRepository;
import com.example.GoCheeta.service.BookingService;
import com.example.GoCheeta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private UserService service;

    @Autowired
    private BookingService serviceB;

    @Autowired
    private BookingRepository bookRepo;

    @GetMapping("")
    public String showHomepage() {

        return "index";
    }

    @GetMapping("/register")
    public String showNewForm(Model model) {
        model.addAttribute("users", new Users());
        model.addAttribute("pageTitle", "Register Form.");
        return "customerRegister";
    }

    @PostMapping("/register_user")
    public String regiUser(Users users, RedirectAttributes ra) {
        service.save(users);
        ra.addFlashAttribute("message", "Registered Succesfully");
        return "redirect:";
    }
    @GetMapping("/login")
    public String login() {

        return "login";
    }
//    @PostMapping("/login")
//    public String login(@ModelAttribute("customer") Customer user, RedirectAttributes ra, Model model) {
//
//        Customer oauthUser = service.log(user.getEmail(),user.getPassword());
//
//        String email = user.getEmail();
//
//        System.out.println(oauthUser);
//        if(Objects.nonNull(oauthUser))
//        {
//            ra.addFlashAttribute("message", "(" +email+  ")");
//
//            return "redirect:/customerHome";
//        } else {
//            ra.addFlashAttribute("message", "Please provide the corret email and password");
//
//
//            return "redirect:/login";
//        }
//    }
    @GetMapping("/customerHome")
    public String customerHome(Model model ) {

        return "customerHome";
    }

    @GetMapping("/customerAddBooking")
    public String booking(@ModelAttribute("customer") Users user, Model model, RedirectAttributes rs) {
        model.addAttribute("booking", new Booking());


        model.addAttribute("pageTitle", "Edit User (ID: " + user + ")");
        return "customerAddBooking";
    }

    @PostMapping("/booking/add")
    public String saveDriver(Booking booking, RedirectAttributes rs) {
        bookRepo.save(booking);
        rs.addFlashAttribute("message", "The Booking has been Successfully Added");
        return "redirect:/";
    }

    @GetMapping("/customerBooking")
    public String showCustomerBooking(Model model) {
        List<Booking> listBooking = serviceB.listAllB();
        model.addAttribute("listBooking", listBooking);
        return "customerBooking";
    }
    @GetMapping("/adminManageUsers")
    public String showCustomerList(Model model) {
        List<Users> listUser = service.listAll();
        model.addAttribute("listUser", listUser);

        return "adminManageUsers";
    }

    @GetMapping("/User/edit/{id}")
    public String customerEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes rs) {
        try {
            Users users = service.get(id);
            model.addAttribute("users", users);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "adminUpdateUsers";
        } catch (UserNotFoundException e) {
           rs.addFlashAttribute("message", "The Customer has been updated successfully");

        }

        return "redirect:/adminManageCustomer";
    }

    @GetMapping("/User/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes rs) {
        try {
            service.delete(id);
            rs.addFlashAttribute("message", "The Customer ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/adminManageUsers";
    }

    @PostMapping("/adminUpdateUsers")
    public String updateCustomer(Users users, RedirectAttributes ra) {
        service.save(users);
        ra.addFlashAttribute("message", "The Customer has been updated successfully");
        return "redirect:adminManageUsers";
    }
}
