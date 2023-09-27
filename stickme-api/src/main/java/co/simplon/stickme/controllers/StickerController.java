package co.simplon.stickme.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.stickme.dtos.StickerAdminItem;
import co.simplon.stickme.dtos.StickerCreate;
import co.simplon.stickme.dtos.StickerDetail;
import co.simplon.stickme.dtos.StickerForUpdate;
import co.simplon.stickme.dtos.StickerItem;
import co.simplon.stickme.dtos.StickerUpdate;
import co.simplon.stickme.services.StickerService;

@RestController
@RequestMapping("/stickers")
public class StickerController {

    @Value("${stickme.cicd.test}")
    private String test;

    private final StickerService service;

    public StickerController(StickerService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(
	    @ModelAttribute @Valid StickerCreate inputs) {
	System.out.println(test);
	service.create(inputs);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id,
	    @ModelAttribute @Valid StickerUpdate inputs) {
	service.update(id, inputs);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
	service.delete(id);
    }

    @GetMapping("/{id}/for-update")
    public StickerForUpdate forUpdate(
	    @PathVariable("id") Long id) {
	return service.forUpdate(id);
    }

    @GetMapping("/{id}/detail")
    public StickerDetail detail(
	    @PathVariable("id") Long id) {
	return service.detail(id);
    }

    @GetMapping
    public Collection<StickerItem> getAll() {
	return service.getAll();
    }

    @GetMapping("/for-edit")
    public Collection<StickerAdminItem> getAllForEdit() {
	return service.getAllForEdit();
    }
}
