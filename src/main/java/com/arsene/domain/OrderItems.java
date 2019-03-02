package com.arsene.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A OrderItems.
 */
@Entity
@Table(name = "order_items")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderItems implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attributes")
    private String attributes;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unity_cost")
    private Double unityCost;

    @OneToMany(mappedBy = "orderItems")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Product> products = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("orderItems")
    private Orders order;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributes() {
        return attributes;
    }

    public OrderItems attributes(String attributes) {
        this.attributes = attributes;
        return this;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getProductName() {
        return productName;
    }

    public OrderItems productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItems quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnityCost() {
        return unityCost;
    }

    public OrderItems unityCost(Double unityCost) {
        this.unityCost = unityCost;
        return this;
    }

    public void setUnityCost(Double unityCost) {
        this.unityCost = unityCost;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public OrderItems products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public OrderItems addProducts(Product product) {
        this.products.add(product);
        product.setOrderItems(this);
        return this;
    }

    public OrderItems removeProducts(Product product) {
        this.products.remove(product);
        product.setOrderItems(null);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Orders getOrder() {
        return order;
    }

    public OrderItems order(Orders orders) {
        this.order = orders;
        return this;
    }

    public void setOrder(Orders orders) {
        this.order = orders;
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
        OrderItems orderItems = (OrderItems) o;
        if (orderItems.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderItems.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderItems{" +
            "id=" + getId() +
            ", attributes='" + getAttributes() + "'" +
            ", productName='" + getProductName() + "'" +
            ", quantity=" + getQuantity() +
            ", unityCost=" + getUnityCost() +
            "}";
    }
}
