package co.simplon.stickme.services;

import org.springframework.stereotype.Service;

import co.simplon.stickme.entities.Sticker;
import co.simplon.stickme.repositories.AspectRepository;
import co.simplon.stickme.repositories.SizeRepository;
import co.simplon.stickme.repositories.StickerRepository;

@Service
// @Transactional(readOnly = true)
public class SpringDataServiceImpl implements SpringDataService {

    private final SizeRepository sizes;

    private final AspectRepository aspects;

    private final StickerRepository stickers;

    public SpringDataServiceImpl(SizeRepository sizes, AspectRepository aspects,
	    StickerRepository stickers) {
	this.sizes = sizes;
	this.aspects = aspects;
	this.stickers = stickers;
    }

    @Override
    public Object execute() {
	Sticker sticker = stickers.findById(15L).get();
	System.out.println(sticker);
	return sticker;
    }

    private Object findByNameAndAspectName() {
	String stickerName = "MongoDB";
	String aspectName = "Matte";
	return stickers.findByNameAndAspectName(stickerName, aspectName);
    }

    private Object findByName() {
	String input = "MongoDB";
	return stickers.findByName(input);
    }
}
