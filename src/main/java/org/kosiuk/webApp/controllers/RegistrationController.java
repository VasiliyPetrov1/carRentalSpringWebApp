package org.kosiuk.webApp.controllers;

import org.kosiuk.webApp.dto.UserRegistrationDto;
import org.kosiuk.webApp.entity.Role;
import org.kosiuk.webApp.entity.User;
import org.kosiuk.webApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/app/registration")
public class RegistrationController {

    private final UserService userService;

    private RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration(@ModelAttribute("userRegDto") UserRegistrationDto userRegDto) {
        return "registration";
    }

    @PostMapping // Map ONLY POST Requests
    public String addNewUser(@ModelAttribute("userRegDto") UserRegistrationDto userRegDto, Model model) {
        // @RequestParam means it is a parameter from the GET or POST request

        try {
            userService.registerUser(userRegDto);
            model.addAttribute("addUserMessage", "You've successfully registered!");
            return "redirect:login";
        } catch (DataIntegrityViolationException e) {
            e.getCause();
            model.addAttribute("addUserMessage", "User with this name is already registered!");
            return "registration";
        }

    }
}
