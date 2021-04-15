package org.kosiuk.webApp.dto;


import org.springframework.stereotype.Component;

@Component
public class CarCreationDto {
        String name;
        String brandName;
        Double rentalPrice;
        Double repairPrice;
        Integer mileage;
        boolean econom;
        boolean premium;
        boolean luxury;

    public CarCreationDto(String name, String brandName, Double rentalPrice, Double repairPrice, Integer mileage,
                          boolean econom, boolean premium, boolean luxury) {
        this.name = name;
        this.brandName = brandName;
        this.rentalPrice = rentalPrice;
        this.repairPrice = repairPrice;
        this.mileage = mileage;
        this.econom = econom;
        this.premium = premium;
        this.luxury = luxury;
    }

    public CarCreationDto(){

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
}
