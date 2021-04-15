package org.kosiuk.webApp.controllers;

import org.kosiuk.webApp.dto.UserCreationDto;
import org.kosiuk.webApp.dto.UserDto;
import org.kosiuk.webApp.entity.Role;
import org.kosiuk.webApp.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/app/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String getUserCreationForm(@ModelAttribute("userCreateDto") UserCreationDto userCreationDto) {
        return "newUser";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("userCreateDto") UserCreationDto userCreationDto, Model model){

        try {
            userService.createUser(userCreationDto);
            model.addAttribute("createUserMessage", "You've successfully created new the user!");
        } catch (DataIntegrityViolationException e) {
            e.getCause();
            model.addAttribute("createUserMessage", "User with this name is already registered!");
            return "newUser";
        }

        return "redirect:/app/user";
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "showAllUsers";
    }

    @PostMapping
    public String showUserByName(@RequestParam String nameFilter, Model model) {
        if (nameFilter != null && !nameFilter.isEmpty()) {
            model.addAttribute("users", Collections.singletonList(userService.getUserByName(nameFilter)));
        } else {
            model.addAttribute("users", userService.getAllUsers());
        }
        return "showAllUsers";
    }

    @GetMapping("{userId}")
    public String getUserEditForm(@PathVariable Integer userId, Model model) { // Spring retrieves User with given ID equal tu user here
        model.addAttribute("userDto", userService.convertUserToDTO(userService.getUserById(userId)));
        model.addAttribute("allRoles", Role.values());
        return "editUser";
    }

    @PostMapping("/{userId}/edit")
    public String updateUser(@ModelAttribute("userDto") UserDto userDto, @PathVariable Integer userId, Model model) {

        try {
            userService.updateUser(userDto);
            model.addAttribute("updateUserMessage", "You've successfully updated the user!");
        } catch (DataIntegrityViolationException e) {
            e.getCause();
            model.addAttribute("updateUserMessage", "User with this name is already registered!");
            return "editUser";
        }

        return "redirect:/app/user";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Integer userId) {

        userService.deleteUser(userId);

        return "redirect:/app/user";
    }

}
