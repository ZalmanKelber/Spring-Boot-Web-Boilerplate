package com.simpleSBApps.webboilerplate.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateUser(String userid, String password) {

        return userid.equalsIgnoreCase("Zalman")
                && password.equalsIgnoreCase("password");
    }

}
