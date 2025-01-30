package com.yassir.bitbox.dto.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yassir.bitbox.models.Item.Item;
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
@Data
@JsonIgnoreProperties(value = {"items"}, allowGetters = false, allowSetters = true)
public class SupplierDTO {
    private Long supplierCode;
    private String name;
    private String country;
    private Set<ItemDTO> items ;
}
