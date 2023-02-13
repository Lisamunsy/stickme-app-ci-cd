package co.simplon.stickme.dtos;

import java.math.BigDecimal;

public interface StickerItem {

    Long getId();

    String getName();

    BigDecimal getPrice();

    String getImageUrl();
}
