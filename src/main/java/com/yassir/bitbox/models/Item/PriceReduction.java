package com.yassir.bitbox.models.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//-------------
@Entity
@Table(name = "discounts")
public class PriceReduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priceReduction", unique = true)
    private Long id;
    @Column(name = "reducedPrice", nullable = false)
    private Long reducedPrice;
    @Column(name = "startDate", nullable = false)
    private Date startDate;
    @Column(name = "finishDate", nullable = false)
    private Date finishDate;
    @Column(name = "item", nullable = false)
    private Item item;
}
