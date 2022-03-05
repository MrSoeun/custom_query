package com.javasql.custom.query.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class EmployeeController {

    @GetMapping("/test")
    public String getEmployee(){
        return "Hello world";
    }
}
