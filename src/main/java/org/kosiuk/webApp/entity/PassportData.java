package org.kosiuk.webApp.entity;

import javax.persistence.*;

@Entity
@Table(name = "passport_data")
public class PassportData {

    @Id
    private Integer id;

    private String name;
    private String surname;

    @Column(name = "patronical_name")
    private String patronicalName;

    @Column(name = "passport_number")
    private Integer passportNumber;

    @Column(name = "rntrc")
    private Integer RNTRC;

    @OneToOne
    @MapsId
    private User user;


    public PassportData(Integer id, String name, String surname, String patronicalName, Integer passportNumber, Integer RNTRC) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronicalName = patronicalName;
        this.passportNumber = passportNumber;
        this.RNTRC = RNTRC;
    }

    public PassportData(String name, String surname, String patronicalName, Integer passportNumber, Integer RNTRC) {
        this.name = name;
        this.surname = surname;
        this.patronicalName = patronicalName;
        this.passportNumber = passportNumber;
        this.RNTRC = RNTRC;
    }


    public PassportData() {

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
