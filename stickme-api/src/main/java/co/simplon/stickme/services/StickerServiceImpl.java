package co.simplon.stickme.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.stickme.dtos.StickerAdminItem;
import co.simplon.stickme.dtos.StickerCreate;
import co.simplon.stickme.dtos.StickerDetail;
import co.simplon.stickme.dtos.StickerForUpdate;
import co.simplon.stickme.dtos.StickerItem;
import co.simplon.stickme.dtos.StickerUpdate;
import co.simplon.stickme.entities.Aspect;
import co.simplon.stickme.entities.Size;
import co.simplon.stickme.entities.Sticker;
import co.simplon.stickme.repositories.AspectRepository;
import co.simplon.stickme.repositories.SizeRepository;
import co.simplon.stickme.repositories.StickerRepository;

@Service
@Transactional(readOnly = true)
public class StickerServiceImpl implements StickerService {

    private final SizeRepository sizes;

    private final AspectRepository aspects;

    private final StickerRepository stickers;

    public StickerServiceImpl(SizeRepository sizes, AspectRepository aspects,
	    StickerRepository stickers) {
	this.sizes = sizes;
	this.aspects = aspects;
	this.stickers = stickers;
    }

    @Transactional // read only = false
    @Override
    public void create(StickerCreate inputs) {
	Sticker entity = new Sticker();
	entity.setName(inputs.getName());
	entity.setDescription(inputs.getDescription());
	entity.setImageUrl(inputs.getImageUrl());
	entity.setPrice(inputs.getPrice());
	Size size = sizes.getReferenceById(inputs.getSizeId());
	entity.setSize(size);
	Aspect aspect = aspects.getReferenceById(inputs.getAspectId());
	entity.setAspect(aspect);
	stickers.save(entity);
    }

    @Override
    @Transactional // read only = false
    public void update(Long id, StickerUpdate inputs) {
	Sticker entity = stickers.findById(id).get();
	entity.setName(inputs.getName());
	entity.setDescription(inputs.getDescription());
	entity.setImageUrl(inputs.getImageUrl());
	entity.setPrice(inputs.getPrice());
	Size size = sizes.getReferenceById(inputs.getSizeId());
	entity.setSize(size);
	Aspect aspect = aspects.getReferenceById(inputs.getAspectId());
	entity.setAspect(aspect);
	stickers.save(entity); // => Useless with @Transactional
    }

    @Override
    public StickerForUpdate forUpdate(Long id) {
	return stickers.findProjectedById(id);
    }

    @Override
    @Transactional // read only = false
    public void delete(Long id) {
	stickers.deleteById(id);
    }

    @Override
    public StickerDetail detail(Long id) {
	return stickers.findProjectedDetailById(id);
    }

    @Override
    public Collection<StickerItem> getAll() {
	return stickers.findAllProjectedByOrderByPrice();
    }

    @Override
    public Collection<StickerAdminItem> getAllForEdit() {
	return stickers.findAllProjectedByOrderByDateAddedDescName();
    }
}
