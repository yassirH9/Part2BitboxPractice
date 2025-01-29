package com.yassir.bitbox.models.products;

import com.yassir.bitbox.enums.ItemStateEnum;
import com.yassir.bitbox.models.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//-------------
@Entity
@Table(name="Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemCode", unique = true)
    private Long itemCode;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ItemStateEnum state;

    // Many-to-many relationship between item and supplier
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_provided",
            joinColumns = @JoinColumn(name = "itemCode"),
            inverseJoinColumns = @JoinColumn(name = "supplierCode")
    )
    private Set<Supplier> suppliers;

    // One-to-many relationship (any item can hold many discounts)
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PriceReduction> priceReductions;

    @Column(name = "creationDate")
    private Date creationDate;

    // One-to-many relationship (an item can have one creator)
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User creator;

    //------------------------------------
    // NON GENERATED BY LOMBOK METHODS
    //------------------------------------
    public void addSupplier(Supplier supplier) {
        this.suppliers.add(supplier);
    }
    public void addPriceReduction(PriceReduction priceReduction){
        this.priceReductions.add(priceReduction);
    }
}
