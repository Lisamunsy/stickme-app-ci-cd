package co.simplon.stickme.dtos;

import java.math.BigDecimal;

public interface StickerDetail {

    String getName();

    String getDescription();

    String getImageUrl();

    BigDecimal getPrice();

    String getSizeName();

    String getAspectName();
}
