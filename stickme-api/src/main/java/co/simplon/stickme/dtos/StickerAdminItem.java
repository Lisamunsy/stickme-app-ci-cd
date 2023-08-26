package co.simplon.stickme.dtos;

import java.time.LocalDate;

public interface StickerAdminItem {

    Long getId();

    String getName();

    String getImageFullName();

    LocalDate getDateAdded();
}
