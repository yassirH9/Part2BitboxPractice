package com.yassir.bitbox.models.Item;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.sql.SQLType;
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
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "finishDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable = false)
    private Item item;
}
