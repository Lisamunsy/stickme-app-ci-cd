package co.simplon.stickme.services;

import java.util.Collection;

import co.simplon.stickme.dtos.StickerAdminItem;
import co.simplon.stickme.dtos.StickerCreate;
import co.simplon.stickme.dtos.StickerDetail;
import co.simplon.stickme.dtos.StickerForUpdate;
import co.simplon.stickme.dtos.StickerItem;
import co.simplon.stickme.dtos.StickerUpdate;

public interface StickerService {

    void create(StickerCreate inputs);

    void update(Long id, StickerUpdate inputs);

    void delete(Long id);

    StickerForUpdate forUpdate(Long id);

    StickerDetail detail(Long id);

    Collection<StickerItem> getAll();

    Collection<StickerAdminItem> getAllForEdit();
}
