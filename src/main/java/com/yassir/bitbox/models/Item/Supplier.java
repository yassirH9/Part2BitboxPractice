package com.yassir.bitbox.models.Item;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//-------------
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierCode", unique = true)
    private Long supplierCode;
    @Column(name = "name", nullable = false)
    private String name;

    //this column should be an enum with countries (?)
    @Column(name = "country", nullable = false)
    private String country;

    @ManyToMany(mappedBy = "suppliers")
    private Set<Item> items;

    public void addItem(Item item){
        this.items.add(item);
    }
}
