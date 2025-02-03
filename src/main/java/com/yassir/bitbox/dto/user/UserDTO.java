package com.yassir.bitbox.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yassir.bitbox.enums.UserPrivilegesEnum;
import lombok.*;


//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//-------------
@Data
@JsonIgnoreProperties(value = {"password"}, allowGetters = false, allowSetters = true) //ignore password just in deserialization process
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private UserPrivilegesEnum privileges;
}
