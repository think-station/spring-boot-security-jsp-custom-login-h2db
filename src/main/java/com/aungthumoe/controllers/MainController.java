package com.aungthumoe.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Aung Thu Moe
 */
@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping({"/index", "/"})
    public String index(Model model) {
        model.addAttribute("title", "Spring Web Application example");
        model.addAttribute("message", " This is Spring Web Application example using Spring boot, JSP");
        LOGGER.debug("Inside MainController.index() method");
        return "index";
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("title", "Spring Security Web Application example");
        model.addAttribute("message", "This is a protected page");
        model.addAttribute("h2", "only user with ADMIN role should be able to access this page!");
        return "admin";
    }

    @RequestMapping(value = "/user**", method = RequestMethod.GET)
    public String userPage(Model model) {
        model.addAttribute("title", "Spring Security Web Application example");
        model.addAttribute("message", "This is a protected page");
        model.addAttribute("h2", "only user with either USER or ADMIN role should be able to access this page!");
        return "user";
    }

    @RequestMapping(value = "/login")
    public String loginPage(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout,
        Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        return "login";
    }

}
