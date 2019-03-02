package com.arsene.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "credit_card")
    private String creditCard;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "day_phone")
    private String dayPhone;

    @Column(name = "eve_phone")
    private String evePhone;

    @Column(name = "mob_phone")
    private String mobPhone;

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ShippingRegion> shippingRegions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public Customer creditCard(String creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getAddress1() {
        return address1;
    }

    public Customer address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public Customer address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public Customer city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public Customer region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Customer postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public Customer country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDayPhone() {
        return dayPhone;
    }

    public Customer dayPhone(String dayPhone) {
        this.dayPhone = dayPhone;
        return this;
    }

    public void setDayPhone(String dayPhone) {
        this.dayPhone = dayPhone;
    }

    public String getEvePhone() {
        return evePhone;
    }

    public Customer evePhone(String evePhone) {
        this.evePhone = evePhone;
        return this;
    }

    public void setEvePhone(String evePhone) {
        this.evePhone = evePhone;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public Customer mobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
        return this;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public Set<ShippingRegion> getShippingRegions() {
        return shippingRegions;
    }

    public Customer shippingRegions(Set<ShippingRegion> shippingRegions) {
        this.shippingRegions = shippingRegions;
        return this;
    }

    public Customer addShippingRegions(ShippingRegion shippingRegion) {
        this.shippingRegions.add(shippingRegion);
        shippingRegion.setCustomer(this);
        return this;
    }

    public Customer removeShippingRegions(ShippingRegion shippingRegion) {
        this.shippingRegions.remove(shippingRegion);
        shippingRegion.setCustomer(null);
        return this;
    }

    public void setShippingRegions(Set<ShippingRegion> shippingRegions) {
        this.shippingRegions = shippingRegions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if (customer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", creditCard='" + getCreditCard() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", city='" + getCity() + "'" +
            ", region='" + getRegion() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", country='" + getCountry() + "'" +
            ", dayPhone='" + getDayPhone() + "'" +
            ", evePhone='" + getEvePhone() + "'" +
            ", mobPhone='" + getMobPhone() + "'" +
            "}";
    }
}
