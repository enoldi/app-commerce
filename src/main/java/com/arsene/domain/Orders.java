package com.arsene.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Orders.
 */
@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "created_on", nullable = false)
    private LocalDate createdOn;

    @Column(name = "shipped_on")
    private LocalDate shippedOn;

    @Column(name = "status")
    private Integer status;

    @Column(name = "comments")
    private String comments;

    @Column(name = "auth_code")
    private String authCode;

    @Column(name = "reference")
    private String reference;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private Customer customer;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private Shipping shipping;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private Tax tax;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public Orders createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getShippedOn() {
        return shippedOn;
    }

    public Orders shippedOn(LocalDate shippedOn) {
        this.shippedOn = shippedOn;
        return this;
    }

    public void setShippedOn(LocalDate shippedOn) {
        this.shippedOn = shippedOn;
    }

    public Integer getStatus() {
        return status;
    }

    public Orders status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public Orders comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAuthCode() {
        return authCode;
    }

    public Orders authCode(String authCode) {
        this.authCode = authCode;
        return this;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getReference() {
        return reference;
    }

    public Orders reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Orders customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public Orders shipping(Shipping shipping) {
        this.shipping = shipping;
        return this;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Tax getTax() {
        return tax;
    }

    public Orders tax(Tax tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
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
        Orders orders = (Orders) o;
        if (orders.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orders.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Orders{" +
            "id=" + getId() +
            ", createdOn='" + getCreatedOn() + "'" +
            ", shippedOn='" + getShippedOn() + "'" +
            ", status=" + getStatus() +
            ", comments='" + getComments() + "'" +
            ", authCode='" + getAuthCode() + "'" +
            ", reference='" + getReference() + "'" +
            "}";
    }
}
