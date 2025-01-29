package com.yassir.bitbox.dto.item;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.enums.ItemStateEnum;
import com.yassir.bitbox.models.Item.PriceReduction;
import com.yassir.bitbox.models.Item.Supplier;
import com.yassir.bitbox.models.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
