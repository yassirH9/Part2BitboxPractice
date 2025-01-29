package com.yassir.bitbox.dto.item;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;


//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//-------------
@Data
@JsonIgnoreProperties(value = {"item"}, allowGetters = false, allowSetters = true)
public class PriceReductionDTO {
    private Long id;
    private Long reducedPrice;
    private Date startDate;
    private Date finishDate;
    private ItemDTO item;
}
