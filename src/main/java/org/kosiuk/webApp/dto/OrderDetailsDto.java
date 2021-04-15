package org.kosiuk.webApp.dto;

import org.springframework.stereotype.Component;

@Component
public class OrderDetailsDto {

    private Integer carId;
    private String carName;
    private String brandName;
    private Double rentalPrice;
    private Double repairPrice;
    private Integer mileage;
    private String imageFileName;
    private String qualClassName;

    private Integer userId;
    private String passportName;
    private String surname;
    private String patronicalName;
    private Integer passportNumber;
    private Integer RNTRC;

    private boolean driverFlag;
    private Integer term;

    private Double driverDailySalary;


    public OrderDetailsDto(Integer carId, String carName, String brandName, Double rentalPrice, Double repairPrice,
                           Integer mileage, String qualClassName, Integer userId, String passportName, String surname,
                           String patronicalName, Integer passportNumber, Integer RNTRC, Double driverDailySalary) {
        this.carId = carId;
        this.carName = carName;
        this.brandName = brandName;
        this.rentalPrice = rentalPrice;
        this.repairPrice = repairPrice;
        this.mileage = mileage;
        this.qualClassName = qualClassName;
        this.userId = userId;
        this.passportName = passportName;
        this.surname = surname;
        this.patronicalName = patronicalName;
        this.passportNumber = passportNumber;
        this.RNTRC = RNTRC;
        this.driverDailySalary = driverDailySalary;
    }

    public OrderDetailsDto(Integer carId, String carName, String brandName, Double rentalPrice, Double repairPrice,
                           Integer mileage, String qualClassName, Integer userId, Double driverDailySalary) {
        this.carId = carId;
        this.carName = carName;
        this.brandName = brandName;
        this.rentalPrice = rentalPrice;
        this.repairPrice = repairPrice;
        this.mileage = mileage;
        this.qualClassName = qualClassName;
        this.userId = userId;
        this.driverDailySalary = driverDailySalary;
    }


    public OrderDetailsDto() {

    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getQualClassName() {
        return qualClassName;
    }

    public void setQualClassName(String qualClassName) {
        this.qualClassName = qualClassName;
    }

    public String getPassportName() {
        return passportName;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronicalName() {
        return patronicalName;
    }

    public void setPatronicalName(String patronicalName) {
        this.patronicalName = patronicalName;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Integer getRNTRC() {
        return RNTRC;
    }

    public void setRNTRC(Integer RNTRC) {
        this.RNTRC = RNTRC;
    }

    public boolean isDriverFlag() {
        return driverFlag;
    }

    public void setDriverFlag(boolean driverFlag) {
        this.driverFlag = driverFlag;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Double getDriverDailySalary() {
        return driverDailySalary;
    }

    public void setDriverDailySalary(Double driverDailySalary) {
        this.driverDailySalary = driverDailySalary;
    }

    public String getImagePath() {
        if (imageFileName == null || carId == null) return null;

        return "/car-images/" + carId + "/" + imageFileName;
    }
}
