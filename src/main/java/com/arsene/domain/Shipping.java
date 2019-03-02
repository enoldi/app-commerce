package com.arsene.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Shipping.
 */
@Entity
@Table(name = "shipping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Shipping implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1000)
    @Column(name = "shipping_type", length = 1000)
    private String shippingType;

    @Column(name = "shipping_cost")
    private Double shippingCost;

    @OneToMany(mappedBy = "shipping")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ShippingRegion> shippingRegions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingType() {
        return shippingType;
    }

    public Shipping shippingType(String shippingType) {
        this.shippingType = shippingType;
        return this;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public Shipping shippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
        return this;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Set<ShippingRegion> getShippingRegions() {
        return shippingRegions;
    }

    public Shipping shippingRegions(Set<ShippingRegion> shippingRegions) {
        this.shippingRegions = shippingRegions;
        return this;
    }

    public Shipping addShippingRegions(ShippingRegion shippingRegion) {
        this.shippingRegions.add(shippingRegion);
        shippingRegion.setShipping(this);
        return this;
    }

    public Shipping removeShippingRegions(ShippingRegion shippingRegion) {
        this.shippingRegions.remove(shippingRegion);
        shippingRegion.setShipping(null);
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
        Shipping shipping = (Shipping) o;
        if (shipping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shipping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Shipping{" +
            "id=" + getId() +
            ", shippingType='" + getShippingType() + "'" +
            ", shippingCost=" + getShippingCost() +
            "}";
    }
}
