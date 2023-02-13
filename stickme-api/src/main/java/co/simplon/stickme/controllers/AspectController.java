package co.simplon.stickme.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.stickme.dtos.LabelValue;
import co.simplon.stickme.services.AspectService;

@RestController
@RequestMapping("/aspects")
public class AspectController {

    private final AspectService service;

    public AspectController(AspectService service) {
	this.service = service;
    }

    @GetMapping
    public Collection<LabelValue> getAll() {
	return service.getAll();
    }
}
