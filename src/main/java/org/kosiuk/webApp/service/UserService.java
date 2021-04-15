package org.kosiuk.webApp.service;

import org.kosiuk.webApp.dto.UserCreationDto;
import org.kosiuk.webApp.dto.UserDto;
import org.kosiuk.webApp.dto.UserRegistrationDto;
import org.kosiuk.webApp.entity.Role;
import org.kosiuk.webApp.entity.User;
import org.kosiuk.webApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void registerUser(UserRegistrationDto userRegDto) {
        User user = new User(userRegDto.getUsername(), userRegDto.getEmail(), userRegDto.getPassword());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        save(user);
    }

    public void createUser(UserCreationDto userCreationDto) throws DataIntegrityViolationException{

        User user = new User(userCreationDto.getUsername(), userCreationDto.getEmail(), userCreationDto.getPassword());

        try {
            save(user);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }

        // roles cant be set for user which wasn't successfully saved to DB

        Integer userId = getUserByName(userCreationDto.getUsername()).getId();

        if (userCreationDto.isUser()) {
            userRepository.createRoles(userId, "USER");
        } else if (userCreationDto.isAdmin()) {
            userRepository.createRoles(userId, "ADMIN");
        } else if (userCreationDto.isManager()) {
            userRepository.createRoles(userId, "MANAGER");
        }

    }

    public Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).get(); // findById returns Optional container here so we need to use get method to retrieve the value
    }

    public void updateUser(UserDto userDto) {

        if (userDto.isUser()) {
            userRepository.updateRoles(userDto.getId(), "USER");
        } else if (userDto.isAdmin()) {
            userRepository.updateRoles(userDto.getId(), "ADMIN");
        } else if (userDto.isManager()) {
            userRepository.updateRoles(userDto.getId(), "MANAGER");
        }

        userRepository.updateUser(userDto.getId(), userDto.getUsername(), userDto.getEmail());

    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDto convertUserToDTO(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(),
                user.getRoles().contains(Role.USER), user.getRoles().contains(Role.ADMIN), user.getRoles().contains(Role.MANAGER));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
