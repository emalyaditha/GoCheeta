package com.example.GoCheeta.controller;

import com.example.GoCheeta.model.Customer;
import com.example.GoCheeta.model.Driver;
import com.example.GoCheeta.repository.DriverRepository;
import com.example.GoCheeta.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class AdminController {

    @Autowired
    private AdminService service;

    @Autowired
    private DriverRepository driverRepo;

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
        if (email.equals("admin@gmail.com") && password.equals("11111")) {
            ra.addFlashAttribute("message", "You have successfully logged in");
            return "redirect:/adminHome";
        }
            ra.addFlashAttribute("message", "Please provide the corret email and password");
            return "redirect:/adminLogin";
    }
    @GetMapping("/adminDriver")
    public String AddDriver(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("pageTitle", "Add Driver");
        return "adminDriver";
    }

    @PostMapping("/driver/add")
    public String saveDriver(Driver driver, RedirectAttributes rs) {
        driverRepo.save(driver);
        rs.addFlashAttribute("message", "The Driver has been Successfully Added");
        return "redirect:/adminManageDriver";
    }

    @GetMapping("/driver")
    public String showDriverList(Model model) {
        List<Driver> listDriver = service.listAll();
        model.addAttribute("listDriver", listDriver);

        return "adminManageDriver";
    }

    @GetMapping("/driver/edit/{id}")
    public String customerEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Driver driver = service.get(id);
            model.addAttribute("driver", driver);
            model.addAttribute("pageTitle", "Edit Driver (ID: " + id + ")");
            return "adminDriver";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "The Driver has been updated successfully");
            return "redirect:/adminManageDriver";
        }
    }

    @GetMapping("/driver/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes rs) {
        try {
            service.delete(id);
            rs.addFlashAttribute("message", "The Customer ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/adminManageDriver";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/";
    }

}
