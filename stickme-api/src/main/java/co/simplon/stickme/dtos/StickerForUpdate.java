package co.simplon.stickme.dtos;

import java.math.BigDecimal;

public interface StickerForUpdate {

    String getName();

    String getDescription();

    BigDecimal getPrice();

    Long getSizeId();

    Long getAspectId();
}
