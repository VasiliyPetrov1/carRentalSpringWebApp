package org.kosiuk.webApp.entity;

import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @Column(name = "quality_class")
    @Enumerated(EnumType.STRING)
    private QualityClass qualityClass;

    @Column(name = "rental_price")
    private Double rentalPrice;

    @Column(name = "repair_price")
    private Double repairPrice;

    private Integer mileage;

    @Column(name="image_file_name", nullable = true, length = 64)
    private String imageFileName;

    @Column(name = "is_in_usage")
    private Boolean inUsage;

    @OneToMany(mappedBy = "car", cascade = CascadeType.MERGE)
    private List<RentalOrder> rentalOrders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    public Car(Integer id, String name, QualityClass qualityClass, Double rentalPrice, Double repairPrice, Integer mileage, Boolean inUsage) {
        this.id = id;
        this.name = name;
        this.qualityClass = qualityClass;
        this.rentalPrice = rentalPrice;
        this.repairPrice = repairPrice;
        this.mileage = mileage;
        this.inUsage = inUsage;
    }

    public Car(String name, QualityClass qualityClass, Double rentalPrice, Double repairPrice, Integer mileage, Boolean isInUsage) {
        this.name = name;
        this.qualityClass = qualityClass;
        this.rentalPrice = rentalPrice;
        this.repairPrice = repairPrice;
        this.mileage = mileage;
        this.inUsage = inUsage;
    }

    public Car() {

    }

    @PrePersist
    public void prePersist() {
        if(inUsage == null) //We set default value in case if the value is not set yet.
            inUsage = false;
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

    public QualityClass getQualityClass() {
        return qualityClass;
    }

    public void setQualityClass(QualityClass qualityClass) {
        this.qualityClass = qualityClass;
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

    public Boolean getInUsage() {
        return inUsage;
    }

    public void setInUsage(Boolean inUsage) {
        this.inUsage = inUsage;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<RentalOrder> getRentalOrders() {
        return rentalOrders;
    }

    public void setRentalOrders(List<RentalOrder> rentalOrders) {
        this.rentalOrders = rentalOrders;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Transient
    public String getImagePath() {
        if (imageFileName == null || id == null) return null;

        return "/car-images/" + id + "/" + imageFileName;
    }
}
