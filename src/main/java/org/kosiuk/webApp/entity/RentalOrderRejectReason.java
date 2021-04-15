package org.kosiuk.webApp.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_reject_reason")
public class RentalOrderRejectReason {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="rental_order_id", length = 500)
    private Integer id;

    private String explanation;

    @OneToOne
    @MapsId
    @JoinColumn(name="rental_order_id")
    private RentalOrder rentalOrder;

    public RentalOrderRejectReason(String explanation) {
        this.explanation = explanation;
    }

    public RentalOrderRejectReason() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public RentalOrder getRentalOrder() {
        return rentalOrder;
    }

    public void setRentalOrder(RentalOrder rentalOrder) {
        this.rentalOrder = rentalOrder;
    }
}
