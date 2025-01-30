package com.yassir.bitbox.models.Item;

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
    private Double reducedPrice;
    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "finishDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable = false)
    private Item item;

    public void addItem(Item item){
        if(this.item==null){
            this.item = item;
        }
    }
}
