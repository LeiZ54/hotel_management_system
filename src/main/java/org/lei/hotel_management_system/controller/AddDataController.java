package org.lei.hotel_management_system.controller;

import org.lei.hotel_management_system.service.AddDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addData")
public class AddDataController {
    @Autowired
    AddDataService addDataService;

    @GetMapping
    public String add() {
        addDataService.add();
        return "SUCCESS!";
    }
}
