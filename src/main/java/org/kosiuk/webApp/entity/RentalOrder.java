package org.kosiuk.webApp.entity;

import javax.persistence.*;

@Entity
@Table(name = "rental_order", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class RentalOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "driver_flag")
    private Boolean driverFlag;

    private Integer term;

    @Enumerated(EnumType.STRING)
    RentalOrderStatus status;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne(mappedBy = "rentalOrder", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private RentalOrderRejectReason orderRejectReason;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private User user;

    public RentalOrder(Integer id, Boolean driverFlag, Integer term, RentalOrderStatus status) {
        this.id = id;
        this.driverFlag = driverFlag;
        this.term = term;
        this.status = status;
    }

    public RentalOrder(Boolean driverFlag, Integer term, RentalOrderStatus status) {
        this.driverFlag = driverFlag;
        this.term = term;
        this.status = status;
    }

    public RentalOrder(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDriverFlag() {
        return driverFlag;
    }

    public void setDriverFlag(Boolean driverFlag) {
        this.driverFlag = driverFlag;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public RentalOrderStatus getStatus() {
        return status;
    }

    public void setStatus(RentalOrderStatus status) {
        this.status = status;
    }

    public RentalOrderRejectReason getOrderRejectReason() {
        return orderRejectReason;
    }

    public void setOrderRejectReason(RentalOrderRejectReason orderRejectReason) {
        this.orderRejectReason = orderRejectReason;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
