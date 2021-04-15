package org.kosiuk.webApp.dto;

import org.springframework.stereotype.Component;

@Component
public class CarDto {

    private Integer id;
    private String name;
    private String brandName;
    private Double rentalPrice;
    private Double repairPrice;
    private Integer mileage;
    private boolean inUsage;
    private String imageFileName;
    private boolean econom;
    private boolean premium;
    private boolean luxury;

    public CarDto(Integer id, String name, String brandName, Double rentalPrice,
                  Double repairPrice, Integer mileage, boolean inUsage) {
        this.id = id;
        this.name = name;
        this.brandName = brandName;
        this.rentalPrice = rentalPrice;
        this.repairPrice = repairPrice;
        this.mileage = mileage;
        this.inUsage = inUsage;
    }

    public CarDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Double getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(Double repairPrice) {
        this.repairPrice = repairPrice;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public boolean isInUsage() {
        return inUsage;
    }

    public void setInUsage(boolean inUsage) {
        this.inUsage = inUsage;
    }

    public boolean isEconom() {
        return econom;
    }

    public void setEconom(boolean econom) {
        this.econom = econom;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public boolean isLuxury() {
        return luxury;
    }

    public void setLuxury(boolean luxury) {
        this.luxury = luxury;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImagePath() {
        if (imageFileName == null || id == null) return null;

        return "/car-images/" + id + "/" + imageFileName;
    }
}
