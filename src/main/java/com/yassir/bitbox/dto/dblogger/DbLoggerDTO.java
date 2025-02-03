package com.yassir.bitbox.dto.dblogger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.user.UserDTO;
import lombok.*;

//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//-------------
@Data
//when deserialization this excludes the elements in null, it's practical when send smaller datasets it's needed
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DbLoggerDTO {
    private Long id;
    private UserDTO userDTO;
    private ItemDTO itemDTO;
    private String reasonMSG;
}
