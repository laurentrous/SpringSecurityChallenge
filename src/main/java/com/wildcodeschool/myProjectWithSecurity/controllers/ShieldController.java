package com.wildcodeschool.myProjectWithSecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShieldController {
    @GetMapping("/")
    public String welcome() {
        return "Welcome to the SHIELD";
    }

    @GetMapping("avengers/assemble")
    public String champion() {
        return "Avengers... Assemble";
    }

    @GetMapping("secret-bases")
    public String director() {
        return "<ul><li>Biarritz</li><li>Bordeaux</li><li>La Loupe</li><li>Lille</li><li>Lyon</li><li>Marseille</li>" +
                "<li>Nantes</li><li>Orléans</li><li>Paris</li><li>Reims</li><li>Strasbourg</li><li>Toulouse</li><li>Tours</li>" +
                "<li>Amsterdam</li><li>Barcelone</li><li>Berlin</li><li>Bruxelles</li><li>Bucarest</li><li>Budapest</li>" +
                "<li>Dublin</li><li>Lisbonne</li><li>Londres</li><li>Madrid</li><li>>Milan</li></ul>";
    }
}