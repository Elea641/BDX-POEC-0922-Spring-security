package com.wildcodeschool.myPorjetWithSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class ShieldController {

    @GetMapping("/")
    public String shield(){
        return "Welcome to the SHIELD";
    }

    @RolesAllowed("CHAMPION")
    @GetMapping("/avengers/assemble")
    public String admin() {
        return "Avengers... Assemble";
    }

    @RolesAllowed("DIRECTOR")
    @GetMapping("/secret-bases")
    public String secretBases() {
        return "https://www.wildcodeschool.com/fr-FR";
    }
}
