package com.pt.springsecurityjpa.model;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long id;

    private String name;

    @Lob
    @Column( length = 100000 )
    private String description;

    @Column(nullable = true, length = 64)
    private String photo;

    private String color;

    private String size;

    private String pocet;




    //Priklad na priame napojenie na jednu tabulku
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;

    @Transient
    public String getPhotosImagePath() {
        if (photo == null || id == null) return null;
        return "/item-photos/" + id + "/" + photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }


    public String getPocet() {
        return pocet;
    }

    public void setPocet(String pocet) {
        this.pocet = pocet;
    }
}
