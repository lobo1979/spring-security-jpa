package com.pt.springsecurityjpa.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id", nullable = false)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;

    private Integer pcs;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "basket_item", joinColumns = @JoinColumn(name = "basket_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }






}
