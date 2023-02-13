package co.simplon.stickme.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class StickerUpdate {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotBlank
    @Size(max = 300)
    private String imageUrl;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("100.0")
    private BigDecimal price;

    @NotNull
    @Positive
    private Long sizeId;

    @NotNull
    @Positive
    private Long aspectId;

    public StickerUpdate() {
	// Required no-arg constructor
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getImageUrl() {
	return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public Long getSizeId() {
	return sizeId;
    }

    public void setSizeId(Long sizeId) {
	this.sizeId = sizeId;
    }

    public Long getAspectId() {
	return aspectId;
    }

    public void setAspectId(Long aspectId) {
	this.aspectId = aspectId;
    }

    @Override
    public String toString() {
	return String.format(
		"{name=%s, description=%s, imageUrl=%s, "
			+ "price=%s, sizeId=%s, aspectId=%s}",
		name, description, imageUrl, price, sizeId, aspectId);
    }
}
