package org.kosiuk.webApp.dto;

import org.springframework.stereotype.Component;

@Component
public class FormedOrderDto {

    private String username;

    private Integer carId;
    private String carName;
    private String imageFileName;
    private Double repairPrice;

    private Integer rentalOrderId;
    private boolean driverFlag;
    private Integer term;
    private String statusName;
    private Double total;
    private String rejectMessageText;

    public FormedOrderDto(String username, Integer carId, String carName, Double repairPrice,
                          Integer rentalOrderId, boolean driverFlag, Integer term, String statusName, Double total) {
        this.username = username;
        this.carId = carId;
        this.carName = carName;
        this.repairPrice = repairPrice;
        this.rentalOrderId = rentalOrderId;
        this.driverFlag = driverFlag;
        this.term = term;
        this.statusName = statusName;
        this.total = total;
    }

    public FormedOrderDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Double getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(Double repairPrice) {
        this.repairPrice = repairPrice;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public Integer getRentalOrderId() {
        return rentalOrderId;
    }

    public void setRentalOrderId(Integer rentalOrderId) {
        this.rentalOrderId = rentalOrderId;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getRejectMessageText() {
        return rejectMessageText;
    }

    public void setRejectMessageText(String rejectMessageText) {
        this.rejectMessageText = rejectMessageText;
    }

    public String getImagePath() {
        if (imageFileName == null || carId == null) return null;

        return "/car-images/" + carId + "/" + imageFileName;
    }
}
