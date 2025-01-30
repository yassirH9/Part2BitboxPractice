package com.yassir.bitbox.dto.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
