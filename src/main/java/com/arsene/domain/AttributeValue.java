package com.arsene.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * AttributeValue entity.
 * @author The Arsene team.
 */
@ApiModel(description = "AttributeValue entity. @author The Arsene team.")
@Entity
@Table(name = "attribute_value")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AttributeValue implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("attributeValues")
    private Attribute attribute;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AttributeValue name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public AttributeValue attribute(Attribute attribute) {
        this.attribute = attribute;
        return this;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
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
        AttributeValue attributeValue = (AttributeValue) o;
        if (attributeValue.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attributeValue.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttributeValue{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
