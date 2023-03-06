package com.bilgeadam.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateRequestDto {
    Long authid;
    String username;
    String email;
    String avatar;
    String password;
    String phone;
    String address;
    String about;
}
