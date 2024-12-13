package fr.fms.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String brand;
    private double price;

    @ManyToOne
    private Category category;//plusieurs articles sont liés à une seule categorie

    public Article() {
    }

    public Article(String description, String brand, double price) {
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

    public Article(Long id, String description, String brand, double price) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

    public Article(String description, String brand, double price, Category category) {
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }
    public String getBrand() {
        return brand;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}