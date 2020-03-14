package com.deba.college.dcollege.controller;

import com.deba.college.dcollege.entity.User;
import com.deba.college.dcollege.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class CollegeController {

    @Autowired
    User user;

    @Autowired
    UserService userService;


    @GetMapping(path = {"/", "/home"})
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        log.info("Inside Home controller...");

        return modelAndView;
    }

    @GetMapping(path = "/registration")
    public ModelAndView getRegistration(ModelAndView model) {

        model.setViewName("registration");
        model.addObject(user);
        log.info("Inside registration get controller...");
        return model;
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        User userExisting = userService.findByEmailId(user.getEmailId());

        if (userExisting != null) {
            bindingResult.rejectValue("userName", null, "There is already one user registered with same " +
                    "user name !!!");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(user);
        log.info("Inside registration save controller... ");
        return "redirect:/registration?success";
    }

    @GetMapping(path = "/login")
    public String getLogin(Model request) {

        log.info(request.toString());
        return "login";
    }
//
//    @PostMapping(path = "/login")
//    public String loginAction(HttpServletRequest request)
//    {
//        log.info("Inside Login Post controller ...");
//        log.info(request.getRequestURI());
//        return "redirect:?success";
//    }

    @GetMapping(path = "/listStudents")
    @ResponseBody
    public List<User> listUser() {
        log.info("Inside list Students controller !!!");

        return userService.listUsers();
    }

    @GetMapping(path = "/accessDenied")
    public String getForbidden()
    {
        return "accessDenied";
    }

}


