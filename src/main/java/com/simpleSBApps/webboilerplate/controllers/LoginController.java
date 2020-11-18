package com.simpleSBApps.webboilerplate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = GET)
    public String getLogin() {
        return "login";
    }
}
