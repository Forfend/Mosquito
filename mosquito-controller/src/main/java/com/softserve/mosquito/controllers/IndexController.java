package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.services.api.UserService;
import com.softserve.mosquito.services.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {
    private UserService userService;
    private UserValidator userValidator;

    @Autowired
    public IndexController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDto>> testIndexController() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }


    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        UserDto foundUser = userService.getUserByEmail(userDto.getEmail());
        if (foundUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user_id", foundUser.getId());
            return ResponseEntity.ok().body(foundUser);
        } else {
            throw new UserNotFoundException();
        }
    }

    @GetMapping(path = "/logout")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("Logged out");
        request.getSession().invalidate();
        return view;
    }

    @PostMapping(path = "/registration")
    @ResponseStatus(HttpStatus.OK)
    public UserDto registration(@Valid @RequestBody UserDto user) {

        if (userValidator.isRegistrationValid(user))
            return user;

        return null;
    }


    private class UserNotFoundException extends RuntimeException {
    }
}