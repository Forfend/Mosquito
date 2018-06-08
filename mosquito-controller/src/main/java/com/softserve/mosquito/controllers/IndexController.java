package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.services.api.PriorityService;
import com.softserve.mosquito.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {
    private UserService userService;


/*    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }
*/
    private PriorityService priorityService;

    @Autowired
    public IndexController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<PriorityDto> testIndexController() {
        return priorityService.getAllPriorities();
    }



    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public UserDto login(@RequestBody UserDto userDto, HttpServletRequest request) {
        UserDto user = userService.getUserByEmail(userDto.getEmail());
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getId());
            return user;
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

    @PostMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView registration(@RequestBody UserDto user) {
        ModelAndView view = new ModelAndView();
        view.addObject(user);
        return view;
    }


    private class UserNotFoundException extends RuntimeException{
    }
}