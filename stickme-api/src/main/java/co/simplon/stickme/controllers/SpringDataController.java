package co.simplon.stickme.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.stickme.services.SpringDataService;

@RestController
@RequestMapping("/spring-data")
public class SpringDataController {

    private final SpringDataService service;

    public SpringDataController(SpringDataService service) {
	this.service = service;
    }

    @GetMapping("/execute")
    public Object execute() {
	return service.execute();
    }
}
