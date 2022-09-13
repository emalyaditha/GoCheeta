package com.example.GoCheeta.controller;
import com.example.GoCheeta.model.Booking;
import com.example.GoCheeta.model.Customer;
import com.example.GoCheeta.repository.BookingRepository;
import com.example.GoCheeta.repository.CustomerRepository;
import com.example.GoCheeta.service.BookingService;
import com.example.GoCheeta.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private BookingService serviceB;

    @Autowired
    private CustomerRepository userRepo;

    @Autowired
    private BookingRepository bookRepo;

    @GetMapping("")
    public String showHomepage() {

        return "index";
    }

    @GetMapping("/register")
    public String showNewForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Register Form.");
        return "customerRegister";
    }

    @PostMapping("/register_user")
    public String regiUser(Customer customer, RedirectAttributes ra) {
        service.save(customer);
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
    @GetMapping("/home")
    public String Home(Model model ) {

        return "home";
    }
    @GetMapping("/customerAddBooking")
    public String booking(@ModelAttribute("customer") Customer user, Model model, RedirectAttributes rs) {
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
