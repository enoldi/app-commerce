package com.arsene.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Column(name = "discounted_price", nullable = false)
    private String discountedPrice;

    @Column(name = "image")
    private String image;

    @Column(name = "image_2")
    private String image2;

    @Column(name = "thumbnail")
    private String thumbnail;

    @NotNull
    @Column(name = "display", nullable = false)
    private Integer display;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "product_category",
               joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "product_attribute",
               joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"))
    private Set<Attribute> attributes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("products")
    private ShoppingCard shoppingCard;

    @ManyToOne
    @JsonIgnoreProperties("products")
    private OrderItems orderItems;

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

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Product description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public Product price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public Product discountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
        return this;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getImage() {
        return image;
    }

    public Product image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage2() {
        return image2;
    }

    public Product image2(String image2) {
        this.image2 = image2;
        return this;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Product thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getDisplay() {
        return display;
    }

    public Product display(Integer display) {
        this.display = display;
        return this;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Product categories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Product addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
        return this;
    }

    public Product removeCategory(Category category) {
        this.categories.remove(category);
        category.getProducts().remove(this);
        return this;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public Product attributes(Set<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Product addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
        attribute.getProducts().add(this);
        return this;
    }

    public Product removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
        attribute.getProducts().remove(this);
        return this;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public ShoppingCard getShoppingCard() {
        return shoppingCard;
    }

    public Product shoppingCard(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
        return this;
    }

    public void setShoppingCard(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public Product orderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", discountedPrice='" + getDiscountedPrice() + "'" +
            ", image='" + getImage() + "'" +
            ", image2='" + getImage2() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", display=" + getDisplay() +
            "}";
    }
}
