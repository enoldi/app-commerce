package com.arsene.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Tax.
 */
@Entity
@Table(name = "tax")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tax implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1000)
    @Column(name = "tax_type", length = 1000)
    private String taxType;

    @Column(name = "tax_percentage")
    private Double taxPercentage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxType() {
        return taxType;
    }

    public Tax taxType(String taxType) {
        this.taxType = taxType;
        return this;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public Tax taxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
        return this;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
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
        Tax tax = (Tax) o;
        if (tax.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tax.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tax{" +
            "id=" + getId() +
            ", taxType='" + getTaxType() + "'" +
            ", taxPercentage=" + getTaxPercentage() +
            "}";
    }
}
