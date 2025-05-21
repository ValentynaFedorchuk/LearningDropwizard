package ua.edu.nung.se.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "fruits")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, name = "refrigeration_required")
    private boolean refrigerationRequired = false;

    @Column(columnDefinition = "TEXT", name = "image_url")
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Гетери та сетери

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRefrigerationRequired() {
        return refrigerationRequired;
    }

    public void setRefrigerationRequired(boolean refrigerationRequired) {
        this.refrigerationRequired = refrigerationRequired;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", refrigerationRequired=" + refrigerationRequired +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return id == fruit.id && refrigerationRequired == fruit.refrigerationRequired && Objects.equals(name, fruit.name) && Objects.equals(imageUrl, fruit.imageUrl) && Objects.equals(description, fruit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, refrigerationRequired, imageUrl, description);
    }
}
