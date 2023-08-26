package co.simplon.stickme.services;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    private final FileStorage storage;

    private final SizeRepository sizes;

    private final AspectRepository aspects;

    private final StickerRepository stickers;

    public StickerServiceImpl(FileStorage storage, SizeRepository sizes,
	    AspectRepository aspects, StickerRepository stickers) {
	this.storage = storage;
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
	entity.setPrice(inputs.getPrice());
	Size size = sizes.getReferenceById(inputs.getSizeId());
	entity.setSize(size);
	Aspect aspect = aspects.getReferenceById(inputs.getAspectId());
	entity.setAspect(aspect);
	MultipartFile file = inputs.getFile();
	String baseName = UUID.randomUUID().toString();
	String fileName = storage.store(file, baseName);
	entity.setImageFullName(fileName);
	stickers.save(entity);
    }

    @Override
    @Transactional // read only = false
    public void update(Long id, StickerUpdate inputs) {
	Sticker entity = stickers.findById(id).get();
	entity.setName(inputs.getName());
	entity.setDescription(inputs.getDescription());
	entity.setPrice(inputs.getPrice());
	Size size = sizes.getReferenceById(inputs.getSizeId());
	entity.setSize(size);
	Aspect aspect = aspects.getReferenceById(inputs.getAspectId());
	entity.setAspect(aspect);
	MultipartFile file = inputs.getFile();
	if (file != null) {
	    String original = entity.getImageFullName();
	    String baseName = UUID.randomUUID().toString();
	    String newFullName = storage.replace(file, baseName, original);
	    entity.setImageFullName(newFullName);
	}
    }

    @Override
    public StickerForUpdate forUpdate(Long id) {
	return stickers.findProjectedById(id);
    }

    @Override
    @Transactional // read only = false
    public void delete(Long id) {
	Sticker entity = stickers.findById(id).get();
	String imageFullName = entity.getImageFullName();
	stickers.delete(entity);
	storage.delete(imageFullName);
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
