package com.yassir.bitbox.dto.item;

import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.enums.ItemStateEnum;

import lombok.*;

import java.util.Date;
import java.util.Set;

//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//-------------
@Data
public class ItemDTO {
    private Long itemCode;
    private String description;
    private Double price;
    private ItemStateEnum state;
    private Set<SupplierDTO> suppliers;
    private Set<PriceReductionDTO> priceReductions;
    private Date creationDate;
    private UserDTO creator;
}
