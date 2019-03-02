package com.arsene.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * The ShoppingCard entity.
 */
@ApiModel(description = "The ShoppingCard entity.")
@Entity
@Table(name = "shopping_card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShoppingCard implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "attributes")
    private String attributes;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "buy_now")
    private Boolean buyNow;

    @Column(name = "added_on")
    private LocalDate addedOn;

    @Column(name = "salary")
    private Long salary;

    @OneToMany(mappedBy = "shoppingCard")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Product> products = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public ShoppingCard itemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getAttributes() {
        return attributes;
    }

    public ShoppingCard attributes(String attributes) {
        this.attributes = attributes;
        return this;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ShoppingCard quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean isBuyNow() {
        return buyNow;
    }

    public ShoppingCard buyNow(Boolean buyNow) {
        this.buyNow = buyNow;
        return this;
    }

    public void setBuyNow(Boolean buyNow) {
        this.buyNow = buyNow;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public ShoppingCard addedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public void setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
    }

    public Long getSalary() {
        return salary;
    }

    public ShoppingCard salary(Long salary) {
        this.salary = salary;
        return this;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public ShoppingCard products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public ShoppingCard addProducts(Product product) {
        this.products.add(product);
        product.setShoppingCard(this);
        return this;
    }

    public ShoppingCard removeProducts(Product product) {
        this.products.remove(product);
        product.setShoppingCard(null);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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
        ShoppingCard shoppingCard = (ShoppingCard) o;
        if (shoppingCard.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shoppingCard.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShoppingCard{" +
            "id=" + getId() +
            ", itemId=" + getItemId() +
            ", attributes='" + getAttributes() + "'" +
            ", quantity=" + getQuantity() +
            ", buyNow='" + isBuyNow() + "'" +
            ", addedOn='" + getAddedOn() + "'" +
            ", salary=" + getSalary() +
            "}";
    }
}
