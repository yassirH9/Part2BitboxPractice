package com.yassir.bitbox.models.Item;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

//    due to bidireccional relationships and problems with maps
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_provided",
            joinColumns = @JoinColumn(name = "supplierCode"),
            inverseJoinColumns = @JoinColumn(name = "itemCode")
    )
    private Set<Item> items;

    public void addItem(Item item){
        if(this.items==null){
            this.items = new HashSet<Item>();
            this.items.add(item);
        }else{
            this.items.add(item);
        }
    }
}
